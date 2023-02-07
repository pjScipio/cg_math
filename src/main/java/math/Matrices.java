/**
 * This file is part of the computer graphics project of the computer graphics group led by
 * Prof. Dr. Philipp Jenke at the University of applied Sciences (HAW) in Hamburg.
 */

package math;

import com.jme3.math.*;

import static math.MathF.cos;
import static math.MathF.sin;

/**
 * A static utility class for creating and applying
 * homogenous transformation matrices of various dimensions.
 * <p>
 * Extends the matrix classes from {@link com.jme3.math}.
 */
public class Matrices {
    private Matrices() {
    }

    // 2D homogeneous transformation matrices
    // ===========================================================================

    /**
     * Creates a 3x3 transformation matrix for 2D,
     * projecting into a new coordinate system spanned by the two basis vectors {@code eX} and {@code eY}.
     */
    public static Matrix3f createCoordinateSystem(Vector2f eX, Vector2f eY) {
        return new Matrix3f(
                eX.x, eY.x, 0,
                eX.y, eY.y, 0,
                0, 0, 1);
    }

    /**
     * Creates a 3x3 transformation matrix for 2D,
     * projecting into a new coordinate system spanned by the two basis vectors {@code eX} and {@code eY},
     * offset by translation vector {@code o}.
     */
    public static Matrix3f createCoordinateSystem(Vector2f eX, Vector2f eY, Vector2f o) {
        return createTranslation(o).mult(createCoordinateSystem(eX, eY));
    }

    /**
     * Creates a 3x3 transformation matrix for 2D,
     * translating by {@code v}.
     */
    public static Matrix3f createTranslation(Vector2f v) {
        return new Matrix3f(
                1, 0, v.x,
                0, 1, v.y,
                0, 0, 1);
    }

    /**
     * Creates a 3x3 transformation matrix for 2D,
     * rotating by angle {@code th} counter-clockwise around the origin.
     *
     * @param th angle in radians
     */
    public static Matrix3f createRotation(float th) {
        return new Matrix3f(
                cos(th), -sin(th), 0,
                sin(th), cos(th), 0,
                0, 0, 1);
    }

    /**
     * Creates a 3x3 transformation matrix for 2D,
     * rotating by angle {@code th} counter-clockwise around the point {@code p}.
     *
     * @param th angle in radians
     */
    public static Matrix3f createRotation(float th, Vector2f p) {
        return createTranslation(p)
                .mult(createRotation(th))
                .mult(createTranslation(p.negate()));
    }

    /**
     * Applies a 3x3 homogeneous transformation matrix to a 2D vector by
     * using matrix multiplication and homogeneous coordinates for {@code v}.
     * <p>
     * May be removed when JME introduces a {@code Matrix3f::mult(Vector2f) -> Vector2f} method.
     */
    public static Vector2f transform(Matrix3f m, Vector2f v) {
        var u = m.mult(new Vector3f(v.x, v.y, 1));
        return new Vector2f(u.x, u.y);
    }

    // 3D homogeneous transformation matrices
    // ===========================================================================

    /**
     * Creates a 4x4 transformation matrix for 3D,
     * projecting into a new coordinate system spanned by the three
     * basis vectors {@code eX}, {@code eY} and {@code eZ}.
     */
    public static Matrix4f createCoordinateSystem(Vector3f eX, Vector3f eY, Vector3f eZ) {
        return new Matrix4f(
                eX.x, eY.x, eZ.x, 0,
                eX.y, eY.y, eZ.y, 0,
                eX.z, eY.z, eZ.z, 0,
                0, 0, 0, 1);
    }

    /**
     * Creates a 4x4 transformation matrix for 3D,
     * projecting into a new coordinate system spanned by the three
     * basis vectors {@code eX}, {@code eY} and {@code eZ},
     * offset by translation vector {@code o}.
     */
    public static Matrix4f createCoordinateSystem(Vector3f eX, Vector3f eY, Vector3f eZ, Vector3f o) {
        return createTranslation(o).mult(createCoordinateSystem(eX, eY, eZ));
    }

    /**
     * Creates a 4x4 transformation matrix for 3D,
     * translating by {@code v}.
     */
    public static Matrix4f createTranslation(Vector3f v) {
        return new Matrix4f(
                1, 0, 0, v.x,
                0, 1, 0, v.y,
                0, 0, 1, v.z,
                0, 0, 0, 1);
    }

    /**
     * Creates a 4x4 transformation matrix for 3D,
     * rotating by angle {@code th} around the axis {@code a} at the origin.
     *
     * @param th angle in radians
     */
    public static Matrix4f createRotation(Vector3f a, float th) {
        var q = new Quaternion().fromAngleAxis(th, a);
        return q.toRotationMatrix(new Matrix4f());
    }

    /**
     * Creates a 4x4 transformation matrix for 3D,
     * rotating by angle {@code th} around the axis {@code a} at the point {@code p}.
     *
     * @param th angle in radians
     */
    public static Matrix4f createRotation(Vector3f a, float th, Vector3f p) {
        return createTranslation(p)
                .mult(createRotation(a, th))
                .mult(createTranslation(p.negate()));
    }

    /**
     * Creates a 4x4 transformation matrix for 3D,
     * scaling along each axis by the individual components of {@code s}.
     */
    public static Matrix4f createScaling(Vector3f s) {
        return new Matrix4f(
                s.x, 0, 0, 0,
                0, s.y, 0, 0,
                0, 0, s.z, 0,
                0, 0, 0, 1);
    }

    // miscellaneous
    // ===========================================================================

    /**
     * Gets the affine transformation part from a 4x4 homogeneous transformation matrix,
     * that is 3x3 matrix A of 4x4 matrix M = [A 0; 0 1];
     */
    public static Matrix3f xyz(Matrix4f m) {
        var a = new Matrix3f();
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                a.set(row, column, m.get(row, column));
            }
        }
        return a;
    }

    public static Matrix3f innerProduct(Vector3f a, Vector3f b) {
        Matrix3f res = new Matrix3f();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                res.set(row, col, a.get(row) * b.get(col));
            }
        }
        return res;
    }

    public static Matrix4f innerProduct(Vector4f a, Vector4f b) {
        Matrix4f res = new Matrix4f();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                res.set(row, col, a.get(row) * b.get(col));
            }
        }
        return res;
    }

    public static Matrix4f makeHomogenious(Matrix3f t) {
        return new Matrix4f(t.get(0, 0), t.get(0, 1), t.get(0, 2), 0,
                t.get(1, 0), t.get(1, 1), t.get(1, 2), 0,
                t.get(2, 0), t.get(2, 1), t.get(2, 2), 0,
                0, 0, 0, 1);
    }

    public static Matrix3f makeHomogeniousTranslationMatrix(Vector2f t) {
        Matrix3f T = new Matrix3f(1, 0, t.x, 0, 1, t.y, 0, 0, 1);
        return T;
    }

    public static Matrix4f makeHomogeniousTranslationMatrix(Vector3f t) {
        Matrix4f T = new Matrix4f(1, 0, 0, t.x,
                0, 1, 0, t.y,
                0, 0, 1, t.z,
                0, 0, 0, 1);
        return T;
    }

    public static Matrix3f getRotation(Matrix4f T) {
        return new Matrix3f(T.m00, T.m01, T.m02,
                T.m10, T.m11, T.m12,
                T.m20, T.m21, T.m22);
    }

    public static Matrix3f makeCoordinateSystemWhereXIs(Vector3f x) {

        Vector3f z = x.cross(Vector3f.UNIT_Y);
        float d = x.normalize().dot(Vector3f.UNIT_Y);
        if (Math.abs(d) > 0.95f) {
            z = x.cross(Vector3f.UNIT_X);
        }

        Vector3f y = z.cross(x);
        x.normalizeLocal();
        y.normalizeLocal();
        z.normalizeLocal();
        Matrix3f T = new Matrix3f(x.x, y.x, z.x, x.y, y.y, z.y, x.z, y.z, z.z);
        return T;
    }

    public static Matrix3f makeCoordinateSystemWhereYIs(Vector3f y) {
        Vector3f z = Vector3f.UNIT_X.cross(y);
        float d = Vector3f.UNIT_X.dot(y.normalize());
        if (Math.abs(d) > 0.95f) {
            z = Vector3f.UNIT_Y.cross(y);
        }
        Vector3f x = y.cross(z);
        x.normalizeLocal();
        y.normalizeLocal();
        z.normalizeLocal();
        Matrix3f T = new Matrix3f(x.x, y.x, z.x, x.y, y.y, z.y, x.z, y.z, z.z);
        return T;
    }

    public static Matrix3f mult(Matrix3f A, Matrix3f B) {
        Matrix3f result = new Matrix3f();
        for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 3; columnIndex++) {
                float value = 0;
                for (int i = 0; i < 3; i++) {
                    value += A.get(rowIndex, i) * B.get(i, columnIndex);
                }
                result.set(rowIndex, columnIndex, value);
            }
        }
        return result;
    }

    public static Matrix3f add(Matrix3f A, Matrix3f B) {
        Matrix3f res = new Matrix3f();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res.set(i, j, A.get(i, j) + B.get(i, j));
            }
        }
        return res;
    }
}
