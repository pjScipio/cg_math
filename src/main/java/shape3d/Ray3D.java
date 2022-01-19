package shape3d;

import com.jme3.math.Vector3f;

import java.util.List;

/**
 * Represents a ray from a starting point towards a dir.
 *
 * @author Philipp Jenke
 */
public class Ray3D implements Shape3D {

  /**
   * Constants
   */
  private static final int X = 0;
  private static final int Z = 2;

  /**
   * Ray p
   */
  private Vector3f p;

  /**
   * Normalized dir vector.
   */
  private Vector3f dir;

  public Ray3D(Vector3f p, Vector3f dir) {
    this.p = p;
    this.dir = dir;
  }

  /**
   * Evaluate the ray, compute the position
   */
  public Vector3f eval(float lambda) {
    return p.add(dir.mult(lambda));
  }

  /**
   * Return distance between ray and point
   */
  public float getDistance(Vector3f p) {
    float lambda = p.subtract(this.p).dot(dir);
    Vector3f q = this.p.add(dir.mult(lambda));
    float distance = q.subtract(p).length();
    return distance;
  }

  public Vector3f getPoint() {
    return p;
  }

  public Vector3f getDir() {
    return dir;
  }

  @Override
  public List<Shape3D> intersect(Shape3D other) {
    return Intersector3D.intersectRayWith(this, other);
  }

  @Override
  public String toString() {
    return "Ray{" +
            "p=" + p +
            ", dir=" + dir +
            '}';
  }

  /**
   * Compute the intersection point of two rays.
   *
   * @param other Other ray used in the computation (together with this-object).
   * @return Parameter values lambda for the this-ray (lambda1) and the other
   * ray (lamda2) at the intersection point. If the rays do not
   * intersect, the method return null.
   */
    /*
    public IntersectionResult intersect(Ray other) {
        float denom = (dir.get(Z) * other.dir.get(X) - dir
                .get(X) * other.dir.get(Z));
        if (Math.abs(denom) < 1e-5) {
            return null;
        }
        float lambda2 = (dir.get(X) * other.p.get(Z) - dir.get(X)
                * p.get(Z) - dir.get(Z) * other.p.get(X) + dir
                .get(Z) * p.get(X))
                / denom;
        float lambda1 = (other.p.get(X) + lambda2 * other.dir.get(X) - p
                .get(X)) / dir.get(X);
        return new IntersectionResult(lambda1, lambda2);
    }
    */
}
