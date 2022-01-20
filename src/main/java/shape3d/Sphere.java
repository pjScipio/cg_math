package shape3d;

import com.jme3.math.Vector3f;

import java.util.List;

public class Sphere implements Shape3D {
  private Vector3f center;
  private float radius;

  public Sphere(Vector3f center, float radius) {
    this.center = new Vector3f(center);
    this.radius = radius;
  }

  public Vector3f getCenter() {
    return center;
  }

  public float getRadius() {
    return radius;
  }

  /**
   * Returns true if the point p is inside the sphere.
   */
  public boolean isInside(Vector3f p) {
    return p.subtract(center).lengthSquared() <= radius * radius;
  }

  @Override
  public List<Shape3D> intersect(Shape3D other) {
    return Intersector3D.intersectSphereWith(this, other);
  }
}
