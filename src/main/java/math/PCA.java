package math;

import Jama.EigenvalueDecomposition;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import misc.Logger;

import java.util.ArrayList;
import java.util.List;

public class PCA {

    /**
     * Centroid
     */
    private Vector3f centroid = null;

    /**
     * Container for the points.
     */
    List<Vector3f> points = new ArrayList<>();

    /**
     * This matrix holds the eigenvalues of the analyzed point set.
     */
    private Jama.Matrix V = null;

    /**
     * This diagonal matrix holds the eigenvectors of the analyzed point set.
     */
    private Jama.Matrix D = null;

    /**
     * Constructor
     */
    public PCA() {
    }

    /**
     * Add an additional point.
     */
    public void add(Vector3f point) {
        points.add(point);
    }

    /**
     * Apply the PCA, compute tangentU, tangentV and normal.
     */
    public void applyPCA() {

        if (points.size() < 3) {
            Logger.getInstance().error("Need a least 3 points for PCA");
            return;
        }

        // Compute centroid
        centroid = new Vector3f();
        for (Vector3f p : points) {
            centroid = centroid.add(p);
        }
        centroid = centroid.mult(1.0f / points.size());

        Matrix3f M = new Matrix3f();
        for (Vector3f p : points) {
            Vector3f d = p.subtract(centroid);
            M = Matrices.add(M, Vectors.dyadic(d, d));
        }

        // Singular value decomposition
        Jama.Matrix jamaM = new Jama.Matrix(3, 3);
        for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
            for (int colIndex = 0; colIndex < 3; colIndex++) {
                jamaM.set(colIndex, rowIndex, M.get(colIndex, rowIndex));
            }
        }
        EigenvalueDecomposition e = jamaM.eig();
        V = e.getV();
        D = e.getD();
    }

    /**
     * Getter.
     */
    public double getEigenValue(int index) {
        return D.get(index, index);
    }

    /**
     * Getter.
     */
    public Vector3f getEigenVector(int index) {
        int dimension = D.getColumnDimension();
        Vector3f eigenVector = new Vector3f();
        for (int i = 0; i < dimension; i++) {
            eigenVector.set(i, (float) V.get(i, index));
        }
        return eigenVector;
    }

    /**
     * Clear list of points.
     */
    public void clear() {
        points.clear();
        D = null;
        V = null;
    }

    public Matrix3f getVDV() {
        Matrix3f v = new Matrix3f();
        Matrix3f d = new Matrix3f();
        Jama.Matrix VV = V.transpose();
        Matrix3f vv = new Matrix3f();

        for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
            for (int colIndex = 0; colIndex < 3; colIndex++) {
                v.set(colIndex, rowIndex, (float) V.get(colIndex, rowIndex));
                d.set(colIndex, rowIndex, (float) D.get(colIndex, rowIndex));
                vv.set(colIndex, rowIndex, (float)VV.get(colIndex, rowIndex));
            }
        }

        Matrix3f result = Matrices.mult(v, d);
        result = Matrices.mult(result, vv);

        return result;
    }

    public Vector3f getCentroid() {
        return centroid;
    }

    public void setCentroid(Vector3f centroid) {
        this.centroid = centroid;
    }

    public Jama.Matrix getV() {
        return V;
    }

    public void setV(Jama.Matrix v) {
        V = v;
    }

    public Jama.Matrix getD() {
        return D;
    }

    public void setD(Jama.Matrix d) {
        D = d;
    }
}
