/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package misc;

import java.util.List;
import java.util.function.BiPredicate;

/**
 * A static utility class for {@link List}.
 */
public final class ListUtil {
  private ListUtil() {
  }

  /**
   * Tests whether two lists a and b are equal using a pairwise predicate.
   * <p>
   * If a and b are both {@code null}, {@code true} is returned.<br/>
   * If exactly one of a or b is {@code null}, {@code false} is returned.<br/>
   * Otherwise, {@code true} is returned if a and b have the same size
   * and each pair per index is equal using the given predicate.
   */
  public static <T> boolean equals(List<T> a, List<T> b, BiPredicate<T, T> predicate) {
    if (a == b) return true;
    if (a == null || b == null) return false;
    if (a.size() != b.size()) return false;

    for (int i = 0; i < a.size(); i++) {
      if (!predicate.test(a.get(i), b.get(i))) {
        return false;
      }
    }
    return true;
  }
}
