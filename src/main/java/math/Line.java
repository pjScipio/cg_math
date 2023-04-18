package math;

import com.google.common.base.MoreObjects;
import com.jme3.math.Vector2f;

import java.util.Objects;

/**
 * A line of infinite length in 2D space, represented by a position and a direction.
 */
public class Line {
  /** A fixed but arbitrary point on the line. */
  private final Vector2f position;

  /** The normalized direction in which the line is pointing. */
  private final Vector2f direction;

  /**
   * Creates a new line from a point on the line and a direction.
   *
   * @param position  Any point on the line.
   * @param direction The direction of the line, will be normalized.
   */
  public Line(Vector2f position, Vector2f direction) {
    this.position = new Vector2f(position);
    this.direction = new Vector2f(direction).normalizeLocal();
  }

  /**
   * Gets a fixed but arbitrary point on the line.
   */
  public Vector2f getPosition() {
    return new Vector2f(position);
  }

  /**
   * Gets the normalized direction of the line.
   */
  public Vector2f getDirection() {
    return new Vector2f(direction);
  }

  /**
   * Evaluates a point on the line, by calculating {@code pos + λ * dir}.
   */
  public Vector2f evaluate(float lambda) {
    return position.add(direction.mult(lambda));
  }

  /**
   * Calculates the intersection point between this and the other line.
   *
   * @return The two line parameters of the intersection point,
   * or {@code null} if the lines are parallel.
   */
  public Intersection intersect(Line other) {
    // solve b = Ax for x
    var a = new Matrix2f(
            direction.x, -other.direction.x,
            direction.y, -other.direction.y);
    var b = other.position.subtract(position);

    Vector2f x;
    try {
      x = a.invertLocal().mult(b);
    } catch (IllegalStateException e) {
      // no solution found
      return null;
    }

    return new Intersection(x.x, x.y);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    var that = (Line) o;
    return position.equals(that.position) &&
            direction.equals(that.direction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, direction);
  }

  @Override
  public String toString() {
    return String.format("%s + λ * %s", position, direction);
  }

  /**
   * An immutable class holding the two line parameters of an intersection point.
   */
  public static class Intersection {
    private final float lambda1;
    private final float lambda2;

    public Intersection(float lambda1, float lambda2) {
      this.lambda1 = lambda1;
      this.lambda2 = lambda2;
    }

    /**
     * Gets the line parameter for the first line,
     * the object on which {@link #intersect(Line)} was called.
     */
    public float getLambda1() {
      return lambda1;
    }

    /**
     * Gets the line parameter for the second line,
     * the object passed via the {@code other} parameter.
     */
    public float getLambda2() {
      return lambda2;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      var that = (Intersection) o;
      return Float.compare(that.lambda1, lambda1) == 0 &&
              Float.compare(that.lambda2, lambda2) == 0;
    }

    @Override
    public int hashCode() {
      return Objects.hash(lambda1, lambda2);
    }

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this)
              .add("lambda1", lambda1)
              .add("lambda2", lambda2)
              .toString();
    }
  }
}
