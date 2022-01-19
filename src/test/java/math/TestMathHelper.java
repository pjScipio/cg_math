package math;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import org.junit.jupiter.api.Test;

import static math.MathF.*;
import static org.junit.jupiter.api.Assertions.*;

class TestMathHelper {
  @Test
  void nChooseK() {
    assertEquals(1, MathHelper.nChooseK(0, 0));

    assertEquals(1, MathHelper.nChooseK(1, 0));
    assertEquals(1, MathHelper.nChooseK(1, 1));

    assertEquals(1, MathHelper.nChooseK(2, 0));
    assertEquals(2, MathHelper.nChooseK(2, 1));
    assertEquals(1, MathHelper.nChooseK(2, 2));

    assertEquals(1, MathHelper.nChooseK(3, 0));
    assertEquals(3, MathHelper.nChooseK(3, 1));
    assertEquals(3, MathHelper.nChooseK(3, 2));
    assertEquals(1, MathHelper.nChooseK(3, 3));

    assertEquals(1, MathHelper.nChooseK(4, 0));
    assertEquals(4, MathHelper.nChooseK(4, 1));
    assertEquals(6, MathHelper.nChooseK(4, 2));
    assertEquals(4, MathHelper.nChooseK(4, 3));
    assertEquals(1, MathHelper.nChooseK(4, 4));

    assertEquals(1, MathHelper.nChooseK(5, 0));
    assertEquals(5, MathHelper.nChooseK(5, 1));
    assertEquals(10, MathHelper.nChooseK(5, 2));
    assertEquals(10, MathHelper.nChooseK(5, 3));
    assertEquals(5, MathHelper.nChooseK(5, 4));
    assertEquals(1, MathHelper.nChooseK(5, 5));
  }

  @Test
  void nChooseKInvalid() {
    assertThrows(ArithmeticException.class, () -> MathHelper.nChooseK(1000, 500));
  }

  @Test
  void normalizeAngle() {
    assertEquals(0f, Angles.normalizeAngle(-TWO_PI), MathHelper.TOLERANCE);
    assertEquals(PI, Angles.normalizeAngle(-PI), MathHelper.TOLERANCE);

    assertEquals(0f, Angles.normalizeAngle(0f), MathHelper.TOLERANCE);
    assertEquals(PI, Angles.normalizeAngle(PI), MathHelper.TOLERANCE);
    assertEquals(0f, Angles.normalizeAngle(TWO_PI), MathHelper.TOLERANCE);

    assertEquals(PI, Angles.normalizeAngle(3f * PI), MathHelper.TOLERANCE);
    assertEquals(0f, Angles.normalizeAngle(4f * PI), MathHelper.TOLERANCE);
  }

  @Test
  void normalizeHalfAngle() {
    assertEquals(-PI, Angles.normalizeHalfAngle(-PI), MathHelper.TOLERANCE);
    assertEquals(-HALF_PI, Angles.normalizeHalfAngle(-HALF_PI), MathHelper.TOLERANCE);
    assertEquals(0f, Angles.normalizeHalfAngle(0f), MathHelper.TOLERANCE);
    assertEquals(HALF_PI, Angles.normalizeHalfAngle(HALF_PI), MathHelper.TOLERANCE);
    assertEquals(-PI, Angles.normalizeHalfAngle(PI), MathHelper.TOLERANCE);
  }

  @Test
  void normalizeMinMaxFloat() {
    assertEquals(0.0f, MathHelper.normalizeMinMax(3f, 3f, 5f));
    assertEquals(0.5f, MathHelper.normalizeMinMax(4f, 3f, 5f));
    assertEquals(1.0f, MathHelper.normalizeMinMax(5f, 3f, 5f));
  }

  @Test
  void normalizeMinMaxDouble() {
    assertEquals(0.0d, MathHelper.normalizeMinMax(3d, 3d, 5d));
    assertEquals(0.5d, MathHelper.normalizeMinMax(4d, 3d, 5d));
    assertEquals(1.0d, MathHelper.normalizeMinMax(5d, 3d, 5d));
  }

  @Test
  void wrap() {
    assertEquals(2, MathHelper.wrap(-4, 3));

    assertEquals(0, MathHelper.wrap(-3, 3));
    assertEquals(1, MathHelper.wrap(-2, 3));
    assertEquals(2, MathHelper.wrap(-1, 3));

    assertEquals(0, MathHelper.wrap(+0, 3));
    assertEquals(1, MathHelper.wrap(+1, 3));
    assertEquals(2, MathHelper.wrap(+2, 3));

    assertEquals(0, MathHelper.wrap(+3, 3));
    assertEquals(1, MathHelper.wrap(+4, 3));
    assertEquals(2, MathHelper.wrap(+5, 3));

    assertEquals(0, MathHelper.wrap(+6, 3));
  }

  @Test
  void lerpFloat() {
    var a = -2f;
    var b = 7f;

    assertEquals(a, MathHelper.lerp(a, b, 0));
    assertEquals(0.25f, MathHelper.lerp(a, b, 0.25f));
    assertEquals(2.50f, MathHelper.lerp(a, b, 0.50f));
    assertEquals(4.75f, MathHelper.lerp(a, b, 0.75f));
    assertEquals(b, MathHelper.lerp(a, b, 1));
  }

  @Test
  void lerpVec2() {
    var a = new Vector2f(-2, 4);
    var b = new Vector2f(4, 5);

    assertEquals(a, MathHelper.lerp(a, b, 0));
    assertEquals(new Vector2f(1, 4.5f), MathHelper.lerp(a, b, 0.5f));
    assertEquals(b, MathHelper.lerp(a, b, 1));
  }

  @Test
  void lerpVec3() {
    var a = new Vector3f(-4, 4, 6);
    var b = new Vector3f(3, 1, 2);

    assertEquals(a, MathHelper.lerp(a, b, 0));
    assertEquals(new Vector3f(-0.5f, 2.5f, 4), MathHelper.lerp(a, b, 0.5f));
    assertEquals(b, MathHelper.lerp(a, b, 1));
  }

  @Test
  void lerpVec4() {
    var a = new Vector4f(-3, -6, 4, 6);
    var b = new Vector4f(0, 3, 5, 8);

    assertEquals(a, MathHelper.lerp(a, b, 0));
    assertEquals(new Vector4f(-1.5f, -1.5f, 4.5f, 7), MathHelper.lerp(a, b, 0.5f));
    assertEquals(b, MathHelper.lerp(a, b, 1));
  }


}
