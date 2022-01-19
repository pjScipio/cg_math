package math;

import base.backend.datastructures.shape2d.Intersector2D;
import base.backend.datastructures.shape2d.Point2D;
import base.backend.datastructures.shape2d.Ray2D;
import base.backend.datastructures.shape2d.Segment2D;
import com.google.common.math.LongMath;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.jme3.math.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MathHelper {

  /**
   * In some calculations, it makes sense to consider two floating-point values equal,
   * if their absolute difference is less than this constant.
   */
  public static final float TOLERANCE = 1e-5f;

  public static double sqr(float x) {
    return x * x;
  }

  /**
   * Compute the center of the incircle of the three segments (= intersection of the bisectors).
   */
  public static Vector2f computeInnerCircleMidpoint(Segment2D... segments) {
    if (segments == null || segments.length != 3) {
      throw new IllegalArgumentException("Only implemented/useful for 3 segments.");
    }

    // Check if pairs of segments are parallel
    boolean[] parallel = new boolean[3];
    for (int i = 0; i < 3; i++) {
      parallel[i] = Math.abs(segments[i].getNormal()
              .dot(segments[(i + 1) % 3].getNormal())) > 1 - MathHelper.TOLERANCE;
    }

    int numberParallel = (parallel[0] ? 1 : 0) + (parallel[1] ? 1 : 0) + (parallel[2] ? 1 : 0);

    // regular case: no two segments parallel
    if (numberParallel == 0) {
      Vector2f[] points = new Vector2f[3];
      Vector2f[] edges = new Vector2f[3];
      Vector2f[] bisectors = new Vector2f[3];
      for (int i = 0; i < 3; i++) {
        Point2D p2d = Intersector2D.intersect(segments[i], segments[(i + 1) % 3]);
        points[i] = p2d.getP();
      }
      for (int i = 0; i < 3; i++) {
        edges[i] = points[(i + 1) % 3].subtract(points[i]).normalize();
      }
      for (int i = 0; i < 3; i++) {
        bisectors[i] = edges[i].subtract(edges[(i + 2) % 3]);
      }
      var aLine = new Segment2D(points[0], bisectors[0]);
      var bLine = new Segment2D(points[1], bisectors[1]);
      var res = Intersector2D.intersect(aLine.toRay(), bLine.toRay());
      Vector2f innerCircleMidPoint = res.getP();

      float[] signedDistance = new float[3];
      boolean allNegative = true;
      for (int i = 0; i < 3; i++) {
        signedDistance[i] = segments[i].signedDistanceTo(innerCircleMidPoint);
        allNegative = allNegative && signedDistance[i] < 0;
      }
      return allNegative ? innerCircleMidPoint : null;

    } else if (numberParallel == 1) {
      int parallelIndex = parallel[0] ? 0 : (parallel[1] ? 1 : 2);
      Vector2f middleDir = segments[parallelIndex].getDirection().normalize();
      float parallelDist = segments[parallelIndex].signedDistanceTo(segments[(parallelIndex + 1) % 3].get(0));
      Vector2f middlePoint = segments[parallelIndex].get(0).add(segments[parallelIndex].getNormal()
              .mult(parallelDist * 0.5f));
      Segment2D otherSeg = segments[(parallelIndex + 2) % 3];
      var middleLine = new Ray2D(middlePoint, middleDir);
      var res = Intersector2D.intersectGetLambdas(otherSeg.toRay(), middleLine);
      Vector2f intersectionPoint = middleLine.eval(res.y);
      Vector2f[] cand = {intersectionPoint.add(middleDir.mult(parallelDist * 0.5f)),
              intersectionPoint.add(middleDir.mult(parallelDist * -0.5f))};

      float[][] signedDistance = new float[2][3];
      boolean[] allNegative = new boolean[2];
      allNegative[0] = allNegative[1] = true;
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 2; j++) {
          signedDistance[j][i] = segments[i].signedDistanceTo(cand[j]);
          allNegative[j] = allNegative[j] && signedDistance[j][i] < 0;
        }
      }

      // Return candidate of it is all positive
      if (allNegative[0]) {
        return cand[0];
      } else if (allNegative[1]) {
        return cand[1];
      } else {
        return null;
      }
    } else {
      throw new IllegalArgumentException("not implemented yet for parallel segments");
    }
  }

  /**
   * Computes "n choose k" (a.k.a "n over k"),
   * that is the binomial coefficient of n and b.
   * <pre>
   * (n k) = n! / ( k! * (n-k)! )
   * </pre>
   *
   * @throws ArithmeticException if the result does not fit into a long
   */
  public static long nChooseK(int n, int k) {
    var nCk = LongMath.binomial(n, k);
    if (nCk != Long.MAX_VALUE) {
      return nCk;
    }
    throw new ArithmeticException(String.format("binomial coefficient (n=%d k=%d) does not fit into a long", n, k));
  }

  /**
   * Return true if the two values are (numerically) equal.
   */
  public static boolean equals(float a, float b) {
    return Math.abs(a - b) < TOLERANCE;
  }



  /**
   * Calculates the modulo of {@code a} and {@code b}.
   * In contrast, the %-operator calculates the remainder.
   */
  public static float mod(float a, float b) {
    return a - b * MathF.floor(a / b);
  }

  /**
   * Calculates the index into a fixed-size circular buffer,
   * wrapping an invalid index at both ends.
   *
   * @param i the possibly invalid index
   * @param n the size of the circular buffer
   * @return a valid index into the circular buffer that would yield the same element
   */
  public static int wrap(int i, int n) {
    return ((i % n) + n) % n;
  }

  /**
   * Normalizes the {@code value} from the range [{@code min}, {@code max}] to the range [0, 1].
   * <p>
   * This is also known as min-max scaling or unity-based normalization.
   */
  public static float normalizeMinMax(float val, float min, float max) {
    return (val - min) / (max - min);
  }

  /**
   * Normalizes the {@code value} from the range [{@code min}, {@code max}] to the range [0, 1].
   * <p>
   * This is also known as min-max scaling or unity-based normalization.
   */
  public static double normalizeMinMax(double val, double min, double max) {
    return (val - min) / (max - min);
  }

  /**
   * Returns {@code val} clamped between {@code min} and {@code max}.
   *
   * @param val the value to ensure between a minimum and a maximum
   * @param min the minimum, must be less than or equal to {@code max}
   * @param max the maximum, must be greater than or equal to {@code min}
   * @return the clamped value
   */
  public static float minmax(float val, float min, float max) {
    return Floats.constrainToRange(val, min, max);
  }

  /**
   * Returns {@code val} clamped between {@code min} and {@code max}.
   *
   * @param val the value to ensure between a minimum and a maximum
   * @param min the minimum, must be less than or equal to {@code max}
   * @param max the maximum, must be greater than or equal to {@code min}
   * @return the clamped value
   */
  public static double minmax(double val, double min, double max) {
    return Doubles.constrainToRange(val, min, max);
  }



  /**
   * Calculates the linear interpolation between two values {@code a} and {@code b}.
   *
   * @param t the interpolation parameter in [0, 1].
   * @return a value in [a, b]
   */
  public static float lerp(float a, float b, float t) {
    return (1f - t) * a + t * b;
  }

  /**
   * Calculates the linear interpolation between two 2D vectors {@code a} and {@code b}.
   *
   * @param t the interpolation parameter in [0, 1].
   * @return a 2D vector in [a, b]
   */
  public static Vector2f lerp(Vector2f a, Vector2f b, float t) {
    return new Vector2f(
            lerp(a.getX(), b.getX(), t),
            lerp(a.getY(), b.getY(), t));
  }

  /**
   * Calculates the linear interpolation between two 3D vectors {@code a} and {@code b}.
   *
   * @param t the interpolation parameter in [0, 1].
   * @return a 3D vector in [a, b]
   */
  public static Vector3f lerp(Vector3f a, Vector3f b, float t) {
    return new Vector3f(
            lerp(a.get(0), b.get(0), t),
            lerp(a.get(1), b.get(1), t),
            lerp(a.get(2), b.get(2), t));
  }

  /**
   * Calculates the linear interpolation between 4D vectors {@code a} and {@code b}.
   *
   * @param t the interpolation parameter in [0, 1].
   * @return a 4D vector in [a, b]
   */
  public static Vector4f lerp(Vector4f a, Vector4f b, float t) {
    return new Vector4f(
            lerp(a.get(0), b.get(0), t),
            lerp(a.get(1), b.get(1), t),
            lerp(a.get(2), b.get(2), t),
            lerp(a.get(3), b.get(3), t));
  }

  public static ColorRGBA lerp(ColorRGBA a, ColorRGBA b, float t) {
    return new ColorRGBA(
            lerp(a.getRed(), b.getRed(), t),
            lerp(a.getGreen(), b.getGreen(), t),
            lerp(a.getBlue(), b.getBlue(), t),
            lerp(a.getAlpha(), b.getAlpha(), t));
  }

  /**
   * Returns value in the range [0, bound),
   * chosen pseudo-randomly from a uniform distribution.
   * <p>
   * The value is sourced from the given RNG.
   *
   * @param bound  the exclusive upper bound, must be positive
   * @param random the source for the random values
   * @throws IllegalArgumentException if bound is not positive
   * @see ThreadLocalRandom#nextDouble(double)
   */
  public static float random(float bound, Random random) {
    if (bound <= 0f) throw new IllegalArgumentException("bound must be positive");
    var result = random.nextFloat() * bound;
    if (result < bound) return result;

    // correct for rounding
    return Math.nextDown(bound);
  }

  /**
   * Returns value in the range [origin, bound),
   * chosen pseudo-randomly from a uniform distribution.
   * <p>
   * The value is sourced from the given RNG.
   *
   * @param origin the inclusive lower bound
   * @param bound  the exclusive upper bound
   * @param random the source for the random values
   * @throws IllegalArgumentException if origin is not less than bound
   * @see ThreadLocalRandom#nextDouble(double, double)
   */
  public static float random(float origin, float bound, Random random) {
    if (origin >= bound) throw new IllegalArgumentException("origin must be less than bound");
    var result = random.nextFloat() * (bound - origin) + origin;
    if (result < bound) return result;

    // correct for rounding
    return Math.nextDown(bound);
  }
}
