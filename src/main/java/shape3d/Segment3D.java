package shape3d;

import com.jme3.math.Vector3f;

import java.util.List;

public class Segment3D implements Shape3D {

  private Vector3f points[];

  public Segment3D() {
    this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));
  }

  public Segment3D(Vector3f a, Vector3f b) {
    points = new Vector3f[]{a, b};
  }

  @Override
  public List<Shape3D> intersect(Shape3D other) {
    return Intersector3D.intersectSegmentWith(this, other);
  }

  public Vector3f getStart() {
    return points[0];
  }

  public Vector3f getEnd() {
    return points[1];
  }
}
