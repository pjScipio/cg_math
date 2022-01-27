/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package shape3d;

import com.jme3.math.Vector3f;
import math.MathF;

import java.util.List;

/**
 * Implementions of intersection computations between 3D shapes.
 */
public class Intersector3D {

  public static List<Shape3D> intersectRayWith(Ray3D ray, Shape3D shape) {
    if (shape instanceof Plane) {
      return intersectRayPlane(ray, (Plane) shape);
    } else if (shape instanceof Sphere) {
      return intersectRaySphere(ray, (Sphere) shape);
    } else {
      System.out.println("Intersection not implemented: " + ray + " <-> " + shape);
      return null;
    }
  }

  public static List<Shape3D> intersectPlaneWith(Plane plane, Shape3D shape) {
    if (shape instanceof Ray3D) {
      return intersectRayPlane((Ray3D) shape, plane);
    } else if (shape instanceof Plane) {
      return intersectPlanePlane(plane, (Plane) shape);
    } else {
      System.out.println("Intersection not implemented: " + plane + " <-> " + shape);
      return null;
    }
  }

  public static List<Shape3D> intersectSphereWith(Sphere sphere, Shape3D shape) {
    if (shape instanceof Ray3D) {
      return intersectRaySphere((Ray3D) shape, sphere);
    } else {
      System.out.println("Intersection not implemented: " + sphere + " <-> " + shape);
      return null;
    }
  }

  public static List<Shape3D> intersectRayPlane(Ray3D ray, Plane plane) {
    float b = plane.getNormal().dot(ray.getDir());
    if (Math.abs(b) < 1e-5) {
      return null;
    }
    float a = plane.getPoint().dot(plane.getNormal()) - plane.getNormal().dot(ray.getPoint());
    float lambda = a / b;
    Vector3f p = ray.eval(lambda);
    return List.of(new Point3D(p));
  }

  public static List<Shape3D> intersectRaySphere(Ray3D ray, Sphere sphere) {
    Vector3f a = sphere.getCenter().subtract(ray.getPoint());
    float aa = a.dot(a);
    float dd = ray.getDir().dot(ray.getDir());
    float rr = sphere.getRadius() * sphere.getRadius();
    float p = -2.0f * a.dot(ray.getDir()) / dd;
    float q = aa - rr / dd;
    float belowSquare = p * p / 4.0f - q;
    if (belowSquare < 0) {
      return List.of();
    } else if (belowSquare == 0) {
      return List.of(new Point3D(ray.eval(-p / 2.0f)));
    } else {
      float lambda1 = -p / 2.0f + MathF.sqrt(belowSquare);
      float lambda2 = -p / 2.0f - MathF.sqrt(belowSquare);
      return List.of(new Point3D(ray.eval(lambda1)), new Point3D(ray.eval(lambda2)));
    }
  }

  public static List<Shape3D> intersectPlanePlane(Plane p1, Plane p2) {
    if (p1.getNormal().normalize().dot(p2.getNormal().normalize()) > 0.98f) {
      // Planes parallel
      return List.of();
    }
    Vector3f dir = p1.getNormal().cross(p2.getNormal());
    float d1 = -p1.getNormal().dot(p1.getPoint());
    float d2 = -p2.getNormal().dot(p2.getPoint());
    Vector3f p = (p2.getNormal().mult(d1).subtract(p1.getNormal().mult(d2)).cross(dir)).mult(-1.0f / dir.dot(dir));
    return List.of(new Ray3D(p, dir));
  }

  public static List<Shape3D> intersectSegmentWith(Segment3D segment3D, Shape3D shape) {
    System.out.println("Intersection not implemented: " + segment3D + " <-> " + shape);
    return null;
  }
}
