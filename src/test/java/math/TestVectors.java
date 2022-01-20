package math;

import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import org.junit.jupiter.api.Test;

import static base.JmeAssertions.assertVecEquals;

public class TestVectors {
  @Test
  public void testXyz() {
    Vector4f v = new Vector4f(1, 2, 3, 4);
    assertVecEquals(new Vector3f(1, 2, 3), Vectors.xyz(v), MathHelper.TOLERANCE);
  }
}
