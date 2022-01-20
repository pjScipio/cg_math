/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package shape3d;

import com.jme3.math.Vector3f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlane {
  @Test
  public void testSignedDistanceTo() {
    Plane plane = new Plane(new Vector3f(0, 0, 0), new Vector3f(0, 1, 0));
    assertEquals(-2, plane.signedDistanceTo(new Vector3f(-2, -2, 0)));
  }
}
