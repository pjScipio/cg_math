/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package base;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * A static utility class for tests,
 * providing means to create arguments for testing function parameters.
 * <p>
 * <strong>Note:</strong><br />
 * This class may be flagged as unused by IDEs because it is not directly used in code,
 * but this is likely a false positive.
 * This class's methods are meant to be used in {@link org.junit.jupiter.params.provider.MethodSource} annotations,
 * and some IDEs do not inspect them.
 */
@SuppressWarnings("unused")
public class ArgumentSources {
  private ArgumentSources() {
  }

  /**
   * Returns a stream with {@code n} values evenly spaced in the closed range [{@code a}, {@code b}].
   *
   * @param a inclusive lower bound, must be less than {@code b}
   * @param b inclusive upper bound, must be greater than {@code a}
   * @param n number of values in stream, must be greater than 2
   */
  static Stream<Float> linSpace(float a, float b, int n) {
    if (b <= a) throw new IllegalArgumentException("a must be less than b");
    if (n < 2) throw new IllegalArgumentException("n must be at least 2");
    return IntStream.range(0, n).mapToObj(i -> {
      // we compute t = i / (n-2) so that t will ranges over [0, 1].
      // just doing t = i / n would yield a half-open range [0, 1).
      var t = i / (float) (n - 1);
      return a + t * (b - a);
    });
  }

  /**
   * Returns the Cartesian product of two streams produced by
   * {@link ArgumentSources#linSpace(float, float, int)}.
   * <p>
   * Example for {@code linSpacePair(a=0, b=2, n=3)}:
   * <pre>(0,0), (0,1), (0,2),   (1,0), (1,1), (1,2),   (2,0), (2,1), (2,2)</pre>
   */
  static Stream<Arguments> linSpacePair(float a, float b, int n) {
    return linSpace(a, b, n).flatMap(x ->
            linSpace(a, b, n).map(y -> arguments(x, y))
    );
  }

  /**
   * Returns a stream with 100 values in [-1, +1].
   */
  public static Stream<Float> rangeNeg1ToPos1() {
    return linSpace(-1, +1, 100);
  }

  /**
   * Returns a stream with 100 values in [-10, +10].
   */
  public static Stream<Float> rangeNeg10ToPos10() {
    return linSpace(-1, +1, 100);
  }

  /**
   * Returns a stream of pairs with each 10 values in [-10, 10],
   * just like the Cartesian product.
   */
  public static Stream<Arguments> rangePairNeg10ToPos10() {
    return linSpacePair(-10, +10, 10);
  }

  /**
   * Returns a stream with 100 values in [-2pi, +2pi].
   */
  public static Stream<Float> rangeRadians() {
    return linSpace((float) (-2 * Math.PI), (float) (2 * Math.PI), 100);
  }

  /**
   * Returns a stream with 100 values in [-360, +360].
   */
  public static Stream<Float> rangeDegrees() {
    return linSpace(-360, +360, 100);
  }
}
