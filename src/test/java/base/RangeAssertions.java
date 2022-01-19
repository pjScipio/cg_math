package base;

import org.junit.jupiter.api.Assertions;

import java.text.MessageFormat;

/**
 * A static utility class for tests,
 * providing methods to assert a value is in a given range.
 */
public class RangeAssertions {
  private static final String MESSAGE_IN_HALF_OPEN = "expected: in range [{0}, {1}) but was: <{2}>";
  private static final String MESSAGE_IN_CLOSED = "expected: in range [{0}, {1}] but was: <{2}>";

  private RangeAssertions() {
  }

  /**
   * <em>Assert</em> that {@code actual} is in half-open range [{@code lowerIncl}, {@code upperExcl}).
   *
   * @see Comparable#compareTo(Object)
   */
  public static <T extends Comparable<T>> void assertInHalfOpen(T lowerIncl, T upperExcl, T actual) {
    if (outOfHalfOpen(lowerIncl, upperExcl, actual)) {
      Assertions.fail(MessageFormat.format(MESSAGE_IN_HALF_OPEN, lowerIncl, upperExcl, actual));
    }
  }

  /**
   * <em>Assert</em> that {@code actual} is in closed range [{@code lowerIncl}, {@code upperIncl}].
   *
   * @see Comparable#compareTo(Object)
   */
  public static <T extends Comparable<T>> void assertInClosed(T lowerIncl, T upperIncl, T actual) {
    if (outOfClosed(lowerIncl, upperIncl, actual)) {
      Assertions.fail(MessageFormat.format(MESSAGE_IN_CLOSED, lowerIncl, upperIncl, actual));
    }
  }

  private static <T extends Comparable<T>> boolean outOfHalfOpen(T lowerIncl, T upperExcl, T value) {
    return value.compareTo(lowerIncl) < 0 ||
            value.compareTo(upperExcl) >= 0;
  }

  private static <T extends Comparable<T>> boolean outOfClosed(T lowerIncl, T upperIncl, T value) {
    return value.compareTo(lowerIncl) < 0 ||
            value.compareTo(upperIncl) > 0;
  }
}
