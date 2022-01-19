package math;

import java.util.concurrent.ThreadLocalRandom;

/**
 * An extension of {@link java.lang.Math} that provides constants and functions
 * with parameter types and return types for single floating-point precision.
 * <p>
 * All constants and functions return values must equal (with respect to less precision)
 * their counterparts from {@link java.lang.Math}.
 */
public final class MathF {
  // all literals in this class used to initialize constants
  // are written as the shortest string, which uniquely represents
  // the floating-point value closest to its real value.

  // TODO: create tests

  /** the constant π: the ratio of a circle's circumference to its diameter (Archimedes' constant) */
  public static final float PI = 3.1415927f;

  /** 2π: the angle of one turn in radians */
  public static final float TWO_PI = 6.2831855f;

  /** π/2: the angle of a quarter turn in radians */
  public static final float HALF_PI = 1.5707964f;

  /** the constant e: the base of the natural logarithm (Euler's number) **/
  public static final float E = 2.7182817f;

  private static final float RAD_TO_DEG = 57.29578f;    // = 1°    / 1 rad = 360° / 2π
  private static final float DEG_TO_RAD = 0.017453292f; // = 1 rad / 1°    = 2π   / 360°

  /**
   * @see Math#sin(double)
   */
  public static float sin(float a) {
    return (float) Math.sin(a);
  }

  /**
   * @see Math#cos(double)
   */
  public static float cos(float a) {
    return (float) Math.cos(a);
  }

  /**
   * @see Math#tan(double)
   */
  public static float tan(float a) {
    return (float) Math.tan(a);
  }

  /**
   * @see Math#asin(double)
   */
  public static float asin(float a) {
    return (float) Math.asin(a);
  }

  /**
   * @see Math#acos(double)
   */
  public static float acos(float a) {
    return (float) Math.acos(a);
  }

  /**
   * @see Math#atan(double)
   */
  public static float atan(float a) {
    return (float) Math.atan(a);
  }

  /**
   * @see Math#atan2(double, double)
   */
  public static float atan2(float y, float x) {
    return (float) Math.atan2(y, x);
  }

  /**
   * @see Math#hypot(double, double)
   */
  public static float hypot(float x, float y) {
    return (float) Math.hypot(x, y);
  }

  /**
   * @see Math#pow(double, double)
   */
  public static float pow(double a, double b) {
    return (float) Math.pow(a, b);
  }

  /**
   * @see Math#sqrt(double)
   */
  public static float sqrt(float a) {
    return (float) Math.sqrt(a);
  }

  /**
   * @see Math#cbrt(double)
   */
  public static float cbrt(float a) {
    return (float) Math.cbrt(a);
  }

  /**
   * @see Math#ceil(double)
   */
  public static float ceil(float a) {
    return (float) Math.ceil(a);
  }

  /**
   * @see Math#floor(double)
   */
  public static float floor(float a) {
    return (float) Math.floor(a);
  }

  /**
   * @see Math#toRadians(double)
   */
  public static float toRadians(float degrees) {
    return degrees * DEG_TO_RAD;
  }

  /**
   * @see Math#toDegrees(double)
   */
  public static float toDegrees(float radians) {
    return radians * RAD_TO_DEG;
  }

  /**
   * Returns value in the range [0, 1),
   * chosen pseudo-randomly from a uniform distribution.
   *
   * @see Math#random()
   */
  public static float random() {
    return ThreadLocalRandom.current().nextFloat();
  }

  /**
   * Returns value in the range [0, bound),
   * chosen pseudo-randomly from a uniform distribution.
   * <p>
   * This is an convenience extension not provided by {@link Math}.
   *
   * @param bound the exclusive upper bound, must be positive
   * @throws IllegalArgumentException if bound is not positive
   */
  public static float random(float bound) {
    return MathHelper.random(bound, ThreadLocalRandom.current());
  }

  /**
   * Returns value in the range [origin, bound),
   * chosen pseudo-randomly from a uniform distribution.
   * <p>
   * This is an convenience extension not provided by {@link Math}.
   *
   * @param origin the inclusive lower bound
   * @param bound  the exclusive upper bound
   * @throws IllegalArgumentException if origin is not less than bound
   */
  public static float random(float origin, float bound) {
    return MathHelper.random(origin, bound, ThreadLocalRandom.current());
  }

  public static float pow(double v, int i) {
    return (float) Math.pow(v, i);
  }

  public static float exp(float v) {
    return (float) Math.exp(v);
  }

  public static float sqr(float v) {
    return (float)MathHelper.sqr(v);
  }
}
