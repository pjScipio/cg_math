package shape3d;

import com.jme3.math.Vector3f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSphere3D {

  @Test
  public void testIsInside() {
    Sphere sphere = new Sphere(new Vector3f(1, 1, 1), 2);
    assertTrue(sphere.isInside(new Vector3f(2, 2, 2)));
    assertFalse(sphere.isInside(new Vector3f(3, 3, 3)));
  }
}
