package math;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;

/**
 * A static utility class for projecting vectors between various dimensions.
 * <p>
 * Extends the vectors classes from {@link com.jme3.math}.
 */
public final class Vectors {
  private Vectors() {
  }

  // 2D
  // ===========================================================================

  /**
   * Creates a new 2D vector from polar coordinates.
   *
   * @param radius the radius of the vector
   * @param angle  the angle of the vector, in radians
   * @return the equivalent cartesian vector
   */
  public static Vector2f fromPolar(float radius, float angle) {
    return new Vector2f(radius * MathF.cos(angle), radius * MathF.sin(angle));
  }

  /**
   * Creates a 2D vector from a 3D vector,
   * mapped via (x, y, z) -> (x, y).
   */
  public static Vector2f xy(Vector3f vector) {
    return new Vector2f(vector.getX(), vector.getY());
  }

  /**
   * Creates a 2D vector from a 3D vector,
   * mapped via (x, y, z) -> (x, z).
   */
  public static Vector2f xz(Vector3f vector) {
    return new Vector2f(vector.getX(), vector.getZ());
  }

  // 3D
  // ===========================================================================

  /**
   * Creates a 3D vector from a 2D vector,
   * mapped via (x, y) -> (x, 0, y).
   */
  public static Vector3f x0y(Vector2f vector) {
    return new Vector3f(vector.getX(), 0, vector.getY());
  }

  /**
   * Creates a 3D vector from a 4D vector,
   * mapped via (x, y, z, w) -> (x, y, z).
   */
  public static Vector3f xyz(Vector4f vector) {
    return new Vector3f(vector.getX(), vector.getY(), vector.getZ());
  }

  /**
   * Creates a 3D vector from an array of float values {x, y, z}.
   */
  public static Vector3f xyz(float[] data) {
    return new Vector3f(data[0], data[1], data[2]);
  }

  public static Vector3f xy1(Vector2f p) {
    return new Vector3f(p.x, p.y, 1);
  }

  // 4D
  // ===========================================================================

  /**
   * Creates a new 4D vector from a 3D vector,
   * mapped via (x, y, z) -> (x, y, z, 1).
   */
  public static Vector4f xyz1(Vector3f v) {
    return new Vector4f(v.x, v.y, v.z, 1);
  }

  // comparison
  // ===========================================================================

  /**
   * Compares two 3D vectors a and b lexicographically,
   * that is interpreted as an ordered sequence of values with
   * descending significance from left to right.
   * <p>
   * Implemented to satisfy {@link java.util.Comparator}.
   *
   * @return an integer indicating if a is less than b (< 0),
   * equal to b (0), or greater than b (> 0)
   */
  public static int compare(Vector3f a, Vector3f b) {
    for (int i = 0; i < 3; i++) {
      if (!MathHelper.equals(a.get(i), b.get(i))) {
        if (a.get(i) < b.get(i)) {
          return -1;
        } else {
          return 1;
        }
      }
    }
    return 0;
  }

  /**
   * Checks whether two 3D vectors a and b are equals.
   * <p>
   * The equality check is done component-wise using
   * {@link MathHelper#equals(float, float)}.
   */
  public static boolean equals(Vector3f a, Vector3f b) {
    if (a == b) return true;
    return MathHelper.equals(a.x, b.x) &&
            MathHelper.equals(a.y, b.y) &&
            MathHelper.equals(a.z, b.z);
  }
}
