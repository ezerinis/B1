package mac_williams_method;

import structures.Matrix;
import structures.Vector;
import utilities.MatrixGenerator;
import utilities.MatrixOperations;
import utilities.VectorOperations;

public class MatrixFinder {

    private MatrixGenerator mg = new MatrixGenerator();
    private MatrixOperations mo = new MatrixOperations();
    private VectorOperations vo = new VectorOperations();

    // Suranda kontroline matrica
    // Paduodama generuojanti matrica ir modulis 'q', grazinama kontroline matrica
    public Matrix findControlMatrix(Matrix gMatrix, int q) throws Exception {
//        if (!checkIfStandardForm(gMatrix)) {
//            throw new Exception("Pateikta generuojanti matrica yra nestandartinio pavidalo");
//        }
        if (gMatrix.getRowCount() == gMatrix.getColumnCount()) {
            throw new Exception("Pateikta generuojanti matrica yra kvadratine, todel negalima rasti jos kontrolines matricos");
        }

        Matrix standardMatrix = findStandardMatrix(gMatrix, q);

        // Nuo generuojancios matricos atskiriama kaireje jos puseje esanti standartinio pavidalo vienetine matrica
        // Gaunama nauja mazesne matrica, kuri turi maziau stulpeliu ir yra sudaryta is generuojancios matricos desiniosios dalies
        Matrix tempMatrix = mo.split(standardMatrix, standardMatrix.getRowCount());

        // Gauta matrica transponuojama
        tempMatrix = mo.transpose(tempMatrix);

        // Transponuotos matricos reiksmes paverciamos neigiamomis ir sutvarkomos pagal moduli 'q'
        tempMatrix = mo.makeNegative(tempMatrix, q);

        // Sukuriama vienetine matrica
        int sideLength = standardMatrix.getColumnCount() - standardMatrix.getRowCount();
        Matrix unitaryMatrix = mg.generateUnitaryMatrix(sideLength, sideLength);

        // Apdorota transponuotoji matrica sujungiama su vienetine matrica
        // Gaunama kontroline matrica
        Matrix controlMatrix = mo.join(tempMatrix, unitaryMatrix);

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

    // Suveda matrica i standartine jos forma
    // paduodama matrica ir modulis 'q', grazinama standartine matrica
    public Matrix findStandardMatrix(Matrix gMatrix, int q) throws Exception {
        Matrix standardMatrix = gMatrix.clone();
        int rowCount = standardMatrix.getRowCount();
        for (int k = 0; k < rowCount;) {
            Vector row = standardMatrix.getVector(k);
            if (row.getC(k) != 0) {
                int inverse = findInverse(row.getC(k), q);
                row = vo.multiply(inverse, row, q);
                standardMatrix.setVector(k, row);
                for (int i = 0; i < rowCount; i++) {
                    if (i == k) {
                        continue;
                    }
                    int multiplier = q - standardMatrix.getVector(i).getC(k);
                    Vector multipliedRow = vo.multiply(multiplier, row, q);
                    Vector tempRow = vo.add(standardMatrix.getVector(i), multipliedRow, q);
                    standardMatrix.setVector(i, tempRow.clone());
                }
                k++;
            } else {
                boolean found = false;
                int i = k + 1;
                while (!found && i < rowCount) {
                    if (standardMatrix.getVector(i).getC(k) != 0) {
                        Vector temp = standardMatrix.getVector(i);
                        standardMatrix.setVector(i, row.clone());
                        standardMatrix.setVector(k, temp);
                        found = true;

                    } else {
                        i++;
                    }
                }
                i = k + 1;
                while (!found && i < standardMatrix.getColumnCount()) {
                    if (standardMatrix.getVector(k).getC(i) != 0) {
                        standardMatrix.rearrange(k, i);
                        found = true;
                    } else {
                        i++;
                    }
                }
                if (!found) {
                    throw new Exception("Matrica neturi standartines formos");
                }
            }
        }
        return standardMatrix;
    }

    // Randa atvirkstini sveiko skaiciaus elementa
    // Paduodamas skaicius ir modulis 'q', grazinamas paduoto skaiciaus atvirkstinis elementas
    private int findInverse(int num, int q) {
        int inverse = 1;
        while (inverse < q) {
            if ((inverse * num) % q == 1) {
                return inverse;
            }
            inverse++;
        }
        return 0;
    }
}
