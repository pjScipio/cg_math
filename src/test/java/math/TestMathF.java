package math;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static base.RangeAssertions.assertInHalfOpen;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestMathF {
  /** tolerance for equality tests of angles unit conversions */
  private static final float ANGLE_CONV_DELTA = 1e-4f;

  @ParameterizedTest
  @MethodSource("base.ArgumentSources#rangeRadians")
  void sin(float input) {
    var expected = (float) Math.sin(input);
    var actual = MathF.sin(input);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("base.ArgumentSources#rangeRadians")
  void cos(float input) {
    var expected = (float) Math.cos(input);
    var actual = MathF.cos(input);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("base.ArgumentSources#rangeRadians")
  void tan(float input) {
    var expected = (float) Math.tan(input);
    var actual = MathF.tan(input);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("base.ArgumentSources#rangeNeg1ToPos1")
  void asin(float input) {
    var expected = (float) Math.asin(input);
    var actual = MathF.asin(input);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("base.ArgumentSources#rangeNeg1ToPos1")
  void acos(float input) {
    var expected = (float) Math.acos(input);
    var actual = MathF.acos(input);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("base.ArgumentSources#rangeNeg1ToPos1")
  void atan(float input) {
    var expected = (float) Math.atan(input);
    var actual = MathF.atan(input);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("base.ArgumentSources#rangePairNeg10ToPos10")
  void atan2(float y, float x) {
    var expected = (float) Math.atan2(y, x);
    var actual = MathF.atan2(y, x);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("base.ArgumentSources#rangePairNeg10ToPos10")
  void hypot(float x, float y) {
    var expected = (float) Math.hypot(x, y);
    var actual = MathF.hypot(x, y);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("base.ArgumentSources#rangePairNeg10ToPos10")
  void pow(float a, float b) {
    var expected = (float) Math.pow(a, b);
    var actual = MathF.pow(a, b);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("base.ArgumentSources#rangeDegrees")
  void sqrt(float input) {
    var expected = (float) Math.sqrt(input);
    var actual = MathF.sqrt(input);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("base.ArgumentSources#rangeDegrees")
  void cbrt(float input) {
    var expected = (float) Math.cbrt(input);
    var actual = MathF.cbrt(input);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("base.ArgumentSources#rangeNeg10ToPos10")
  void ceil(float input) {
    var expected = (float) Math.ceil(input);
    var actual = MathF.ceil(input);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("base.ArgumentSources#rangeNeg10ToPos10")
  void floor(float input) {
    var expected = (float) Math.floor(input);
    var actual = MathF.floor(input);
    assertEquals(expected, actual);
  }

  @ParameterizedTest
  @MethodSource("base.ArgumentSources#rangeDegrees")
  void toRadians(float input) {
    var expected = (float) Math.toRadians(input);
    var actual = MathF.toRadians(input);
    assertEquals(expected, actual, ANGLE_CONV_DELTA);
  }

  @ParameterizedTest
  @MethodSource("base.ArgumentSources#rangeRadians")
  void toDegrees(float input) {
    var expected = (float) Math.toDegrees(input);
    var actual = MathF.toDegrees(input);
    assertEquals(expected, actual, ANGLE_CONV_DELTA);
  }

  @RepeatedTest(1000)
  void random() {
    var actual = MathF.random();
    assertInHalfOpen(0f, 1f, actual);
  }

  @RepeatedTest(1000)
  void randomOrigin() {
    var actual = MathF.random(100);
    assertInHalfOpen(0f, 100f, actual);
  }

  @RepeatedTest(1000)
  void randomOriginBound() {
    var actual = MathF.random(100, 200);
    assertInHalfOpen(100f, 200f, actual);
  }
}
