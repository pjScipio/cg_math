package math;

import com.jme3.math.Vector2f;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A ray in 2D space, represented by a starting point and a direction.
 */
public class Ray {
  /**
   * Ray p
   */
  private final Vector2f p;

  /**
   * Normalized dir vector.
   */
  private final Vector2f dir;

  /**
   * Creates a new ray.
   *
   * @param p   The starting point of the ray.
   * @param dir The direction of the ray, will be normalized.
   */
  public Ray(Vector2f p, Vector2f dir) {
    this.p = p;
    this.dir = dir.normalize();
  }

  /**
   * Evaluate the ray, compute the position.
   *
   * @throws IllegalArgumentException If lambda is less than zero.
   */
  public Vector2f eval(float lambda) {
    checkArgument(lambda >= 0, "lambda must be in [0, ∞)");
    return p.add(dir.mult(lambda));
  }

  /**
   * Return distance between ray and point
   */
  public float getDistance(Vector2f p) {
    float lambda = p.subtract(this.p).dot(dir);
    Vector2f q = this.p.add(dir.mult(lambda));
    return q.subtract(p).length();
  }

  public Vector2f getPoint() {
    return p;
  }

  public Vector2f getDir() {
    return dir;
  }

  /**
   * Creates a new line extending this ray.
   */
  public Line toLine() {
    return new Line(p, dir);
  }

  /**
   * Calculates the intersection point between this and the other ray.
   *
   * @return The two ray parameters of the intersection point,
   * or {@code null} if the rays do not intersect.
   */
  public Line.Intersection intersect(Ray other) {
    var intersect = toLine().intersect(other.toLine());
    if (intersect == null) {
      // rays are parallel
      return null;
    }
    if (intersect.getLambda1() < 0 || intersect.getLambda2() < 0) {
      // intersection point does not lie on both rays
      return null;
    }
    return intersect;
  }

  @Override
  public String toString() {
    return p + " + λ * " + dir;
  }
}
