package math;

import com.jme3.math.Vector3f;

/**
 * Representation of a unit quaternion, used as rotation.
 */
public class UnitQuaternion {
    private final float[] values;

    public UnitQuaternion() {
        values = new float[]{0, 1, 0, 0};
    }

    public UnitQuaternion(float x, float y, float z, float w) {
        values = new float[]{x, y, z, w};
    }

    public static UnitQuaternion fromAxisAngle(float angle, Vector3f axis) {
        Vector3f a = axis.normalize();
        float angleHalf = angle / 2.0f;
        float sinAngle = MathF.sin(angleHalf);
        return new UnitQuaternion(MathF.cos(angleHalf),
                a.x * sinAngle,
                a.y * sinAngle,
                a.z * sinAngle);
    }

    /**
     * Return a SLERP interpolation object between the two rotations specified by p and q.
     */
    public static Slerp slerp(UnitQuaternion p, UnitQuaternion q) {
        return new Slerp(p, q);
    }

    /**
     * Compute the dot product between this and the other quaternion.
     */
    public float dot(UnitQuaternion q) {
        return values[0] * q.values[0] + values[1] * q.values[1] + values[2] * q.values[2] + values[3] * q.values[3];
    }

    /**
     * Compute the hamilton product between this and q.
     */
    public UnitQuaternion hamilton(UnitQuaternion q) {
        return new UnitQuaternion(
                values[0] * q.values[0] - values[1] * q.values[1] - values[2] * q.values[2] - values[3] * q.values[3],
                values[0] * q.values[1] + values[1] * q.values[0] + values[2] * q.values[3] - values[3] * q.values[2],
                values[0] * q.values[2] - values[1] * q.values[3] + values[2] * q.values[0] + values[3] * q.values[1],
                values[0] * q.values[3] + values[1] * q.values[2] - values[2] * q.values[1] + values[3] * q.values[0]);
    }

    /**
     * Return a new quaternion as the sum of the this quaternion and q.
     */
    public UnitQuaternion add(UnitQuaternion q) {
        return new UnitQuaternion(values[0] + q.values[0], values[1] + q.values[1], values[2] + q.values[2], values[3] + q.values[3]);
    }

    /**
     * Compute and return the norm of the unit quaternion - should always be 1.
     */
    private float norm() {
        return MathF.sqrt(conjugate().dot(this));
    }

    /**
     * Return the conjugate.
     */
    public UnitQuaternion conjugate() {
        return new UnitQuaternion(values[0], -values[1], -values[2], -values[3]);
    }

    public UnitQuaternion inverse() {
        return conjugate().mult(1.0f / norm());
    }

    /**
     * Return the result of multiplying this with a scalar.
     */
    private UnitQuaternion mult(float s) {
        return new UnitQuaternion(values[0] * s, values[1] * s, values[2] * s, values[3] * s);
    }

    @Override
    public String toString() {
        return String.format("(%.2f,%.2f,%.2f,%.2f)", values[0], values[1], values[2], values[3]);
    }

    public Vector3f rotate(Vector3f p) {
        UnitQuaternion q = this.hamilton(
                new UnitQuaternion(0, p.x, p.y, p.z)
                        .hamilton(conjugate()));
        return new Vector3f(q.values[1], q.values[2], q.values[3]);

    }

    /**
     * Representation of a spherical interpolation between two rotations
     * represented by quaternions.
     */
    public static class Slerp {
        private UnitQuaternion p, q;
        private float sinTheta;

        public Slerp(UnitQuaternion p, UnitQuaternion q) {
            this.p = p;
            this.q = q;
            float cosTheta = p.dot(q);
            this.sinTheta = MathF.sqrt(1 - cosTheta * cosTheta);
        }

        public UnitQuaternion interpolate(float t) {
            return p.mult(sinTheta * (1 - t))
                    .add(q.mult(sinTheta * t))
                    .mult(1.0f / sinTheta);
        }
    }
}
