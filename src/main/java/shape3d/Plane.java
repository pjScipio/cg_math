package shape3d;

import com.jme3.math.Vector3f;

import java.util.List;

/**
 * A plane in 3D space, represented in normal form.
 */
public class Plane implements Shape3D {

  /**
   * Point on plane
   */
  private Vector3f p;

  /**
   * Normal
   */
  private Vector3f n;

  public Plane(Vector3f p, Vector3f n) {
    this.p = new Vector3f(p);
    this.n = new Vector3f(n);
  }

  public Vector3f getNormal() {
    return n;
  }

  public Vector3f getPoint() {
    return p;
  }

  /**
   * Return a flipped instance of the plane.
   */
  public Plane getFlipped() {
    return new Plane(p, n.mult(-1));
  }

  public void setPoint(Vector3f p) {
    this.p = p;
  }

  public void setNormal(Vector3f n) {
    this.n = n;
  }

  public float unsignedDistanceTo(Vector3f x) {
    return Math.abs(signedDistanceTo(x));
  }

  public float signedDistanceTo(Vector3f x) {
    return x.dot(n) - p.dot(n);
  }

  @Override
  public String toString() {
    return "p: " + p + ", n: " + n;
  }

  @Override
  public List<Shape3D> intersect(Shape3D other) {
    return Intersector3D.intersectPlaneWith(this, other);
  }

  public boolean isInPositiveHalfSpace(Vector3f point) {
    return signedDistanceTo(point) >= 0;
  }
}
