/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

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
