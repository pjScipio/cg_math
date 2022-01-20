/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package misc;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestListUtil {
  @Test
  void testEquals_SameRef() {
    var a = List.of();
    assertTrue(ListUtil.equals(a, a, Object::equals));
  }

  @Test
  void testEquals_Null() {
    assertTrue(ListUtil.equals(null, null, Object::equals));
    assertFalse(ListUtil.equals(List.of(), null, Object::equals));
    assertFalse(ListUtil.equals(null, List.of(), Object::equals));
  }

  @Test
  void testEquals_Size() {
    assertTrue(ListUtil.equals(List.of(), List.of(), Object::equals));
    assertFalse(ListUtil.equals(List.of(), List.of(1), Object::equals));
  }

  @Test
  void testEquals_Pred() {
    assertTrue(ListUtil.equals(List.of(1, 2), List.of(1, 2), Object::equals));
    assertFalse(ListUtil.equals(List.of(1, 2), List.of(2, 1), Object::equals));

    assertTrue(ListUtil.equals(List.of(1), List.of(9), (a, b) -> true));
    assertFalse(ListUtil.equals(List.of(1), List.of(1), (a, b) -> false));
  }
}
