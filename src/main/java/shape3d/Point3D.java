package shape3d;

import com.jme3.math.Vector3f;

import java.util.List;

public class Point3D implements Shape3D {
  private Vector3f p;

  public Point3D(Vector3f p) {
    this.p = new Vector3f(p);
  }

  public Vector3f getPoint() {
    return p;
  }

  @Override
  public List<Shape3D> intersect(Shape3D other) {
    return null;
  }
}
