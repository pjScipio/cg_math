package math;

import com.jme3.math.Vector3f;
import org.junit.jupiter.api.Test;

public class TestPCA {
    @Test
    public void test() {
        PCA pca = new PCA();
        for (int i = 0; i < 10; i++) {
            //System.out.println("Vector " + (i + 1) + ":");
            Vector3f v = new Vector3f();
            v.set(0, MathF.random() * 2);
            // System.out.println("Wert 0 = " + v.get(0));
            for (int j = 1; j < 3; j++) {
                v.set(j, MathF.random());
                // System.out.println("Wert " + j + " = " + v.get(j));
            }
            pca.add(v);
            //System.out.println(v);
        }
        pca.applyPCA();
    }
}
