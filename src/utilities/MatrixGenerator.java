package utilities;

import java.util.Random;
import structures.Matrix;
import structures.Vector;

public class MatrixGenerator {

    // Generuoja atsitiktine generuojancia matrica, kuri yra standartinio pavidalo
    // Paduodama n - matricos stulpeliu skaicius, k - matricos eiluciu skaicius ir modulis 'q'
    // Grazinama atsitiktine standartinio pavidalo matrica
    public Matrix generate(int n, int k, int q) throws Exception {
        Matrix matrix = new Matrix(k);
        Random rand = new Random();
        for (int i = 0; i < k; i++) {
            Vector row = new Vector(new int[n], q);
            row.setC(i, 1);
            for (int j = 0; j < n - k; j++) {
                row.setC(k + j, rand.nextInt(q));
            }
            matrix.setVector(i, row.clone());
        }
        return matrix;
    }
}
