package shape3d;

import com.jme3.math.Vector3f;
import math.MathHelper;
import org.junit.jupiter.api.Test;

import static base.JmeAssertions.assertVecEquals;

public class TestRay3D {

  @Test
  public void testEval() {
    Ray3D ray3D = new Ray3D(new Vector3f(1, 1, 1), new Vector3f(0, 2, 0));
    assertVecEquals(new Vector3f(1, 3, 1), ray3D.eval(1), MathHelper.TOLERANCE);
  }
}
