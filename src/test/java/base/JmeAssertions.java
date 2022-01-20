/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package base;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import org.junit.jupiter.api.Assertions;

/**
 * A static utility class for tests,
 * providing methods to assert equality with tolerance of JME classes.
 */
@SuppressWarnings("ConstantConditions")
public class JmeAssertions {
  private JmeAssertions() {
  }

  /**
   * <em>Assert</em> that each pair of components from {@code expected} and {@code actual}
   * are equal within the given non-negative {@code delta}.
   */
  public static void assertVecEquals(Vector2f expected, Vector2f actual, float delta) {
    assertNullity(expected, actual);
    Assertions.assertAll(
            () -> Assertions.assertEquals(expected.getX(), actual.getX(), delta),
            () -> Assertions.assertEquals(expected.getY(), actual.getY(), delta));
  }

  /**
   * <em>Assert</em> that each pair of components from {@code expected} and {@code actual}
   * are equal within the given non-negative {@code delta}.
   */
  public static void assertVecEquals(Vector3f expected, Vector3f actual, float delta) {
    assertNullity(expected, actual);
    Assertions.assertAll(
            () -> Assertions.assertEquals(expected.get(0), actual.get(0), delta),
            () -> Assertions.assertEquals(expected.get(1), actual.get(1), delta),
            () -> Assertions.assertEquals(expected.get(2), actual.get(2), delta));
  }

  private static void assertNullity(Object expected, Object actual) {
    // if one is null, we cannot check by components, so assert if both are null
    if (expected == null || actual == null) {
      Assertions.assertEquals(expected, actual);
    }
  }
}
