package mac_williams_method;

import structures.Matrix;
import structures.Vector;
import utilities.MatrixGenerator;
import utilities.MatrixOperations;
import utilities.VectorOperations;

public class ControlMatrixFinder {

    private MatrixGenerator mg = new MatrixGenerator();
    private MatrixOperations mo = new MatrixOperations();
    private VectorOperations vo = new VectorOperations();

    // Suranda kontroline matrica
    // Paduodama generuojanti matrica ir modulis 'q', grazinama kontroline matrica
    public Matrix findControlMatrix(Matrix gMatrix, int q) throws Exception {
        if (!checkIfStandardForm(gMatrix)) {
            throw new Exception("Pateikta generuojanti matrica yra nestandartinio pavidalo");
        }
        if (gMatrix.getRowCount() == gMatrix.getColumnCount()) {
            throw new Exception("Pateikta generuojanti matrica yra kvadratine, todel negalima rasti jos kontrolines matricos");
        }

        // Nuo generuojancios matricos atskiriama kaireje jos puseje esanti standartinio pavidalo vienetine matrica
        // Gaunama nauja mazesne matrica, kuri turi maziau stulpeliu ir yra sudaryta is generuojancios matricos desiniosios dalies
        Matrix tempMatrix = mo.split(gMatrix, gMatrix.getRowCount());

        // Gauta matrica transponuojama
        tempMatrix = mo.transpose(tempMatrix);

        // Transponuotos matricos reiksmes paverciamos neigiamomis ir sutvarkomos pagal moduli 'q'
        tempMatrix = mo.makeNegative(tempMatrix, q);

        // Sukuriama standartinio pavidalo vienetine matrica
        int sideLength = gMatrix.getColumnCount() - gMatrix.getRowCount();
        Matrix standardMatrix = mg.generateUnitaryMatrix(sideLength, sideLength);

        // Apdorota transponuotoji matrica sujungiama su standartinio pavidalo vienetine matrica
        // Gaunama kontroline matrica
        Matrix controlMatrix = mo.join(tempMatrix, standardMatrix);

        return controlMatrix;
    }

    // Patikrina ar matrica standartinio pavidalo
    // Paduodama matrica, gazinama logine reiksme 'true' arba 'false'
    private boolean checkIfStandardForm(Matrix matrix) {
        int columnCount = matrix.getColumnCount();
        int rowCount = matrix.getRowCount();
        if (columnCount < rowCount) {
            return false;
        }
        for (int k = 0; k < rowCount; k++) {
            Vector row = matrix.getVector(k);
            for (int n = 0; n < k; n++) {
                if (row.getC(n) != 0) {
                    return false;
                }
            }
            for (int n = k + 1; n < rowCount; n++) {
                if (row.getC(n) != 0) {
                    return false;
                }
            }
            if (row.getC(k) != 1) {
                return false;
            }
        }
        return true;
    }

    private Matrix findStandardMatrix(Matrix gMatrix, int q) throws Exception {
        Matrix standardMatrix = gMatrix.clone();
        int rowCount = standardMatrix.getRowCount();
        for (int k = 0; k < rowCount;) {
            Vector row = standardMatrix.getVector(k);
            if (row.getC(k) != 0) {
                row = vo.add(q - row.getC(k) + 1, row, q);
                for (int i = 0; i < standardMatrix.getRowCount(); i++) {
                    if (i == k) {
                        continue;
                    }
                    int multiplier = q - standardMatrix.getVector(i).getC(k);
                    Vector multipliedRow = vo.multiply(multiplier, row, q);
                    Vector tempRow = vo.add(standardMatrix.getVector(i), multipliedRow, q);
                    standardMatrix.setVector(i, tempRow);
                }
            } else {
                
            }
        }
        return standardMatrix;
    }
}
