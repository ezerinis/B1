package mac_williams_method;

import structures.Matrix;
import structures.Vector;
import utilities.MathOperations;

public class DualCodeMatrixFinder {

    MathOperations mo = new MathOperations();

    // Suranda dualaus kodo generuojancia matrica
    // Paduodama generuojanti matrica ir modulis 'q', grazinama dualaus kodo generuojanti matrica
    public Matrix find(Matrix gMatrix, int q) throws Exception {
        int dualMColumns = gMatrix.getColumnCount();
        int dualMRows = dualMColumns - gMatrix.getRowCount();

        Matrix dualCodeMatrix = new Matrix(dualMRows);
        Vector gVector = new Vector(dualMColumns);
        int dualMRowNumber = 0;
        while (dualMRowNumber < dualMRows) {
            mo.incrementVector(gVector, q);
            boolean stop = false;
            for (int i = 0; i < gMatrix.getRowCount() && !stop; i++) {
                int scalarMulti = mo.scalarMultiply(gVector, gMatrix.getVector(i), q);
                if (scalarMulti != 0) {
                    stop = true;
                }
            }
            if (!stop) {
                dualCodeMatrix.setVector(dualMRowNumber++, new Vector(gVector.getData(), q));
            }
        }

        return dualCodeMatrix;
    }
}
