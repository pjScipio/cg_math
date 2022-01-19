package misc;

import com.jme3.math.Vector3f;

/**
 * Represents an axis-aligned bounding box in 3D space.
 *
 * @author Philipp Jenke
 */

public class AxisAlignedBoundingBox {
  private static final int DIMENSION = 3;

  /**
   * Lower left corner.
   */
  private Vector3f ll = null;

  /**
   * Upper right corner
   */
  private Vector3f ur = null;

  public AxisAlignedBoundingBox() {
  }

  public Vector3f getLL() {
    return ll;
  }

  public Vector3f getUR() {
    return ur;
  }

  public void add(Vector3f point) {
    if (ll == null) {
      ll = new Vector3f(point);
    }
    if (ur == null) {
      ur = new Vector3f(point);
    }
    for (int i = 0; i < DIMENSION; i++) {
      if (point.get(i) < ll.get(i)) {
        ll.set(i, point.get(i));
      }
      if (point.get(i) > ur.get(i)) {
        ur.set(i, point.get(i));
      }
    }
  }

  public Vector3f getExtent() {
    return ur.subtract(ll);
  }

  public Vector3f getCenter() {
    return ll.add(ur).mult(0.5f);
  }

  @Override
  public String toString() {
    return ll.toString() + " -> " + ur.toString();
  }

  public void add(AxisAlignedBoundingBox boundingBox) {
    add(boundingBox.ll);
    add(boundingBox.ur);
  }
}
