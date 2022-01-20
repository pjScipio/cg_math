/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package math;

import com.jme3.math.Vector2f;

import java.util.Arrays;

/**
 * A 2x2 matrix, built after the matrix classes of JME.
 */
public class Matrix2f {
  public static final int ROWS = 2;
  public static final int COLS = 2;

  private final float[][] data;

  /**
   * Creates a new identity matrix.
   */
  public Matrix2f() {
    this.data = new float[][]{{1, 0}, {0, 1}};
  }

  /**
   * Creates a new matrix with specified values.
   */
  public Matrix2f(float m00, float m01, float m10, float m11) {
    this.data = new float[][]{{m00, m01}, {m10, m11}};
  }

  /**
   * Creates a new matrix that is the copy of another.
   */
  public Matrix2f(Matrix2f that) {
    this.data = new float[ROWS][COLS];
    for (int row = 0; row < ROWS; row++) {
      System.arraycopy(that.data[row], 0, this.data[row], 0, COLS);
    }
  }

  /**
   * Returns the calculated determinant.
   */
  public float determinant() {
    return data[0][0] * data[1][1] - data[0][1] * data[1][0];
  }

  /**
   * Sets this matrix to its inverse.
   *
   * @return itself
   * @throws IllegalStateException if the matrix is not invertible,
   *                               that is when {@link Matrix2f#determinant()} returns {@code 0}
   */
  public Matrix2f invertLocal() {
    var det = determinant();
    if (det == 0) {
      throw new IllegalStateException("not invertible, determinant is zero");
    }
    var a = data[0][0];
    var b = data[0][1];
    var c = data[1][0];
    var d = data[1][1];
    this.data[0][0] = d / det;
    this.data[0][1] = -b / det;
    this.data[1][0] = -c / det;
    this.data[1][1] = a / det;
    return this;
  }

  /**
   * Returns a new matrix that is the inverse of this matrix.
   *
   * @return a new instance
   * @throws IllegalStateException if the matrix is not invertible,
   *                               that is when {@link Matrix2f#determinant()} returns {@code 0}
   */
  public Matrix2f invert() {
    var copy = new Matrix2f(this);
    return copy.invertLocal();
  }

  /**
   * Sets this matrix {@code M} to the result of the scalar multiplication {@code λM}.
   *
   * @param scl the scalar {@code λ}
   * @return itself
   */
  public Matrix2f multLocal(float scl) {
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        this.data[row][col] *= scl;
      }
    }
    return this;
  }

  /**
   * Multiplies a vector by this matrix and stores the result in that passed vector.
   *
   * @return the parameter {@code vec}
   */
  public Vector2f multLocal(Vector2f vec) {
    float x = vec.x;
    float y = vec.y;
    vec.x = data[0][0] * x + data[0][1] * y;
    vec.y = data[1][0] * x + data[1][1] * y;
    return vec;
  }

  /**
   * Sets this matrix {@code M} to the result of the matrix multiplication {@code MR} with some other matrix {@code R}.
   *
   * @param rhs the other matrix {@code R}
   * @return itself
   */
  public Matrix2f multLocal(Matrix2f rhs) {
    var copy = new Matrix2f(this);
    this.data[0][0] = copy.data[0][0] * rhs.data[0][0] + copy.data[0][1] * rhs.data[1][0];
    this.data[0][1] = copy.data[0][0] * rhs.data[0][1] + copy.data[0][1] * rhs.data[1][1];
    this.data[1][0] = copy.data[1][0] * rhs.data[0][0] + copy.data[1][1] * rhs.data[1][0];
    this.data[1][1] = copy.data[1][0] * rhs.data[0][1] + copy.data[1][1] * rhs.data[1][1];
    return this;
  }

  /**
   * Returns a new matrix that is the result of the scalar multiplication {@code λM}.
   *
   * @param scl the scalar {@code λ}
   * @return a new instance
   */
  public Matrix2f mult(float scl) {
    var copy = new Matrix2f(this);
    return copy.multLocal(scl);
  }

  /**
   * Returns a new vector that is the result of the matrix-vector multiplication {@code Mv}.
   *
   * @param vec the vector {@code v}
   * @return a new instance
   */
  public Vector2f mult(Vector2f vec) {
    var copy = new Vector2f(vec);
    return multLocal(copy);
  }

  /**
   * Returns a new matrix that is the result of the matrix multiplication {@code MR}.
   *
   * @param mat the other matrix {@code R}
   * @return a new instance
   */
  public Matrix2f mult(Matrix2f mat) {
    var copy = new Matrix2f(this);
    return copy.multLocal(mat);
  }

  /**
   * Gets the value of the element at the given position.
   *
   * @param row the row index ({@code i})
   * @param col the column index ({@code j})
   */
  public float get(int row, int col) {
    return data[row][col];
  }

  /**
   * Sets the value of the element at the given position.
   *
   * @param row   the row index ({@code i})
   * @param col   the column index ({@code j})
   * @param value the new value
   */
  public void set(int row, int col, float value) {
    data[row][col] = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Matrix2f that = (Matrix2f) o;
    return Arrays.deepEquals(data, that.data);
  }

  @Override
  public int hashCode() {
    return Arrays.deepHashCode(data);
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder("Matrix2f[\n");
    for (int row = 0; row < ROWS; row++) {
      str.append("  ");
      for (int col = 0; col < COLS; col++) {
        str.append(data[row][col]).append(" ");
      }
      str.append("\n");
    }
    return str + "]";
  }
}
