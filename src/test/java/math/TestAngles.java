package math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAngles {

  @Test
  public void testRandomAngle() {
    float angle = Angles.randomAngle();
    assertTrue(angle >= 0 && angle <= MathF.TWO_PI);
  }
}
