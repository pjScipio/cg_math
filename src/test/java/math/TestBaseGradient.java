package math;

import base.frontend.color.BaseGradient;
import math.MathHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TestBaseGradient {
  FloatGradient gradient;

  @BeforeEach
  void beforeEach() {
    gradient = new FloatGradient();
  }

  @Test
  void none() {
    assertNull(gradient.get(0.0f));
    assertNull(gradient.get(0.5f));
    assertNull(gradient.get(1.0f));
  }

  @Test
  void one() {
    gradient.add(0.5f, 0.0f);

    assertEquals(0.0f, gradient.get(0.0f));
    assertEquals(0.0f, gradient.get(0.5f));
    assertEquals(0.0f, gradient.get(1.0f));
  }

  @Test
  void twoDiff() {
    gradient.add(0.25f, 0.0f);
    gradient.add(0.75f, 1.0f);

    assertEquals(0.0f, gradient.get(0.00f));
    assertEquals(0.0f, gradient.get(0.25f));
    assertEquals(0.5f, gradient.get(0.50f));
    assertEquals(1.0f, gradient.get(0.75f));
    assertEquals(1.0f, gradient.get(1.00f));
  }

  @Test
  void twoSame() {
    gradient.add(0.25f, 0.5f);
    gradient.add(0.75f, 0.5f);

    assertEquals(0.5f, gradient.get(0.00f));
    assertEquals(0.5f, gradient.get(0.25f));
    assertEquals(0.5f, gradient.get(0.50f));
    assertEquals(0.5f, gradient.get(0.75f));
    assertEquals(0.5f, gradient.get(1.00f));
  }

  static class FloatGradient extends BaseGradient<Float> {
    @Override
    protected Float interpolate(Float a, Float b, float i) {
      return MathHelper.lerp(a, b, i);
    }
  }
}
