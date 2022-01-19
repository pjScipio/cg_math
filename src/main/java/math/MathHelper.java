package math;

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
