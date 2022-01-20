/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package math;

import com.jme3.math.Vector2f;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestMatrix2f {
  Matrix2f mat;

  @BeforeEach
  void beforeAll() {
    mat = new Matrix2f(2, 1, 6, 4);
  }

  @Test
  void determinant() {
    var expected = 2;
    var actual = mat.determinant();

    assertEquals(expected, actual);
  }

  @Test
  void invert() {
    var expected = new Matrix2f(2, -0.5f, -3, 1);

    var copy = mat.invert();
    var self = mat.invertLocal();

    assertEquals(expected, copy);
    assertNotSame(mat, copy);
    assertEquals(expected, self);
    assertSame(mat, self);
  }

  @Test
  void multScl() {
    var scale = 2;
    var expected = new Matrix2f(4, 2, 12, 8);

    var copy = mat.mult(scale);
    var self = mat.multLocal(scale);

    assertEquals(expected, copy);
    assertNotSame(mat, copy);
    assertEquals(expected, self);
    assertSame(mat, self);
  }

  @Test
  void multVec() {
    var vec = new Vector2f(2, 5);
    var expected = new Vector2f(9, 32);

    var copy = mat.mult(vec);
    var self = mat.multLocal(vec);

    assertEquals(expected, copy);
    assertNotSame(vec, copy);
    assertEquals(expected, self);
    assertSame(vec, self);
  }

  @Test
  void multMat() {
    var rhs = new Matrix2f(3, -2, -4, 5);
    var expected = new Matrix2f(2, 1, 2, 8);

    var copy = mat.mult(rhs);
    var self = mat.multLocal(rhs);

    assertEquals(expected, copy);
    assertNotSame(mat, copy);
    assertEquals(expected, self);
    assertSame(mat, self);
  }

  @Test
  void get() {
    assertEquals(2, mat.get(0, 0));
    assertEquals(1, mat.get(0, 1));
    assertEquals(6, mat.get(1, 0));
    assertEquals(4, mat.get(1, 1));
  }

  @Test
  void set00() {
    mat.set(0, 0, 3);
    assertEquals(new Matrix2f(3, 1, 6, 4), mat);
  }

  @Test
  void set01() {
    mat.set(0, 1, 2);
    assertEquals(new Matrix2f(2, 2, 6, 4), mat);
  }

  @Test
  void set10() {
    mat.set(1, 0, 7);
    assertEquals(new Matrix2f(2, 1, 7, 4), mat);
  }

  @Test
  void set11() {
    mat.set(1, 1, 5);
    assertEquals(new Matrix2f(2, 1, 6, 5), mat);
  }

  @Test
  void testEquals() {
    var equalMat = new Matrix2f(2, 1, 6, 4);
    var diffMat = new Matrix2f(4, 6, 2, 1);

    assertEquals(equalMat, mat);
    assertNotEquals(diffMat, mat);
  }

  @Test
  void testHashCode() {
    var expected = new Matrix2f(2, 1, 6, 4).hashCode();
    var unexpected = new Matrix2f(4, 6, 2, 1).hashCode();

    var actual = mat.hashCode();

    assertEquals(expected, actual);
    assertNotEquals(unexpected, actual);
  }
}
