package math;

import com.jme3.math.Matrix4f;
import com.jme3.math.Vector3f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMatrices {
  @Test
  public void testCreateScaling() {
    Matrix4f T = Matrices.createScaling(new Vector3f(1, 2, 3));
    assertEquals(1, T.m00);
    assertEquals(0, T.m01);
    assertEquals(0, T.m02);
    assertEquals(0, T.m03);
    assertEquals(0, T.m10);
    assertEquals(2, T.m11);
    assertEquals(0, T.m12);
    assertEquals(0, T.m13);
    assertEquals(0, T.m20);
    assertEquals(0, T.m21);
    assertEquals(3, T.m22);
    assertEquals(0, T.m23);
    assertEquals(0, T.m30);
    assertEquals(0, T.m31);
    assertEquals(0, T.m32);
    assertEquals(1, T.m33);
  }
}
