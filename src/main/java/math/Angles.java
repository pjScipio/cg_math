package math;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

public class Angles {
  public static float toRadiens(float alpha) {
    return alpha / 180.0f * MathF.PI;
  }

  public static float degree2radiens(float degree) {
    return degree * MathF.PI / 180.0f;
  }

  /**
   * Compute the angle between 2D vectors u and v. u and v must be normalized.
   */
  public static float angleBetween2D(Vector2f u, Vector2f v) {
    if (!MathHelper.equals(u.length(), 1) || !MathHelper.equals(v.length(), 1)) {
      throw new IllegalArgumentException("Vectors must be normalized");
    }

    var a = MathF.atan2(u.getY(), u.getX());
    var b = MathF.atan2(v.getY(), v.getX());
    return normalizeAngle(b - a);
  }

  /**
   * Compute the angle between 2D vectors u and v.
   */
  public static float angleBetween3D(Vector3f u, Vector3f v, Vector3f normal) {
    u = u.normalize();
    v = v.normalize();

    var dot = u.dot(v);
    if (Math.abs(dot - 1) < MathHelper.TOLERANCE) {
      return 0;
    } else if (Math.abs(dot) + 1 < MathHelper.TOLERANCE) {
      return MathF.PI;
    }
    var angle = MathF.acos(dot);
    Vector3f baseDir = normal.cross(u);
    if (baseDir.dot(v) < 0) {
      angle = -angle;
    }
    return angle;
  }

  /**
   * Returns the {@code angle} normalized to the range [0, 2π).
   */
  public static float normalizeAngle(float angle) {
    return MathHelper.mod(angle, MathF.TWO_PI);
  }

  /**
   * Returns the {@code angle} normalized to the range [-π, π).
   */
  public static float normalizeHalfAngle(float angle) {
    angle = normalizeAngle(angle);
    if (angle >= Math.PI) {
      angle -= MathF.TWO_PI;
    }
    return angle;
  }

  /**
   * Returns a random float value in the range [0, 2π).
   */
  public static float randomAngle() {
    return MathF.random(MathF.TWO_PI);
  }
}
