/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package shape3d;

import com.jme3.math.Vector3f;
import math.MathHelper;
import org.junit.jupiter.api.Test;

import static base.JmeAssertions.assertVecEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestIntersector3D {
  @Test
  public void testIntersectPlanePlane() {
    Plane plane1 = new Plane(new Vector3f(1, 1, 0), new Vector3f(1, 0, 0));
    Plane plane2 = new Plane(new Vector3f(1, 1, 0), new Vector3f(0, 1, 0));
    var res = Intersector3D.intersectPlanePlane(plane1, plane2);
    assertTrue(res.size() == 1);
    Ray3D resRay = (Ray3D) res.get(0);
    assertEquals(1, Math.abs(resRay.getDir().dot(new Vector3f(0, 0, 1))), MathHelper.TOLERANCE);
  }

  @Test
  public void testIntersectRayPlane() {
    Plane plane = new Plane(new Vector3f(0, 0, 0), new Vector3f(0, -1, 0));
    Ray3D ray = new Ray3D(new Vector3f(1, 1, 0), new Vector3f(0, 1, 0));
    var res = Intersector3D.intersectRayPlane(ray, plane);
    assertTrue(res.size() == 1);
    Point3D resPoint = (Point3D) res.get(0);
    assertVecEquals(new Vector3f(1, 0, 0), resPoint.getPoint(), MathHelper.TOLERANCE);
  }

  @Test
  public void testIntersectRaySphere() {
    Sphere sphere = new Sphere(new Vector3f(0, 0, 0), 1);
    Ray3D ray = new Ray3D(new Vector3f(0, 5, 0), new Vector3f(0, 1, 0));
    var res = Intersector3D.intersectRaySphere(ray, sphere);
    assertTrue(res.size() == 2);
    Point3D resPoint1 = (Point3D) res.get(0);
    Point3D resPoint2 = (Point3D) res.get(1);
    boolean p1Found = new Vector3f(0, 1, 0).subtract(resPoint1.getPoint()).length() < MathHelper.TOLERANCE ||
            new Vector3f(0, -1, 0).subtract(resPoint1.getPoint()).length() < MathHelper.TOLERANCE;
    boolean p2Found = new Vector3f(0, 1, 0).subtract(resPoint2.getPoint()).length() < MathHelper.TOLERANCE ||
            new Vector3f(0, -1, 0).subtract(resPoint2.getPoint()).length() < MathHelper.TOLERANCE;
    assertTrue(p1Found);
    assertTrue(p2Found);
  }
}
