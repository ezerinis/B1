package utilities;

import structures.Matrix;
import structures.Vector;

public class MatrixOperations {

    // Padalina matrica i dvi dalis ir grazina desiniaja jos dali
    // Paduodama matrica ir stulpelio numeris, nuo kurio padalinama matrica
    public Matrix split(Matrix inputMatrix, int splittingColumn) {
        int rowCount = inputMatrix.getRowCount();
        Matrix splittedMatrix = new Matrix(rowCount);
        for (int k = 0; k < rowCount; k++) {
            int columnCount = inputMatrix.getColumnCount() - splittingColumn;
            Vector splittedRow = new Vector(columnCount);
            Vector originalRow = inputMatrix.getVector(k);
            int columnIndex = 0;
            for (int n = splittingColumn; n < inputMatrix.getColumnCount(); n++) {
                splittedRow.setC(columnIndex++, originalRow.getC(n));
            }
            splittedMatrix.setVector(k, splittedRow);
        }
        return splittedMatrix;
    }

    // Transponuoja matrica
    // Paduodama matrica, grazinama transponuota matrica
    public Matrix transpose(Matrix inputMatrix) {
        int tRowCount = inputMatrix.getColumnCount();
        Matrix transposedMatrix = new Matrix(tRowCount);
        for (int k = 0; k < tRowCount; k++) {
            int tColumnCount = inputMatrix.getRowCount();
            Vector transposedRow = new Vector(tColumnCount);
            for (int n = 0; n < tColumnCount; n++) {
                transposedRow.setC(n, inputMatrix.getVector(n).getC(k));
            }
            transposedMatrix.setVector(k, transposedRow);
        }
        return transposedMatrix;
    }

    // Apskaiciuoja neigiamas matricos reiksmes moduliu 'q'
    // Paduodama matrica su moduliu 'q', grazinama apdorota matrica
    public Matrix makeNegative(Matrix inputMatrix, int q) {
        int rowCount = inputMatrix.getRowCount();
        Matrix result = new Matrix(rowCount);
        for (int k = 0; k < rowCount; k++) {
            Vector originalRow = inputMatrix.getVector(k);
            int size = originalRow.getSize();
            Vector newRow = new Vector(size);
            for (int n = 0; n < size; n++) {
                newRow.setC(n, (q - originalRow.getC(n)) % q);
            }
            result.setVector(k, newRow);
        }
        return result;
    }

    // Sujungia dvi matricas su sutampanciais eiluciu kiekiais
    // Paduodamos dvi matricos, grazinama matrica, kurios eiluciu kiekis toks pat, o stulpeliu kiekis lygus ivestu matricu stulpeliu sumai
    public Matrix join(Matrix matrix1, Matrix matrix2) throws Exception {
        if (matrix1.getRowCount() != matrix2.getRowCount()) {
            throw new Exception("Matricu eiluciu kiekiai nesutampa");
        }
        int rowCount = matrix1.getRowCount();
        Matrix joinedMatrix = new Matrix(rowCount);
        int joinedColumnCount = matrix1.getColumnCount() + matrix2.getColumnCount();
        for (int k = 0; k < rowCount; k++) {
            Vector joinedRow = new Vector(joinedColumnCount);
            int i = 0;
            Vector originalRow1 = matrix1.getVector(k);
            for (int n1 = 0; n1 < matrix1.getColumnCount(); n1++) {
                joinedRow.setC(i++, originalRow1.getC(n1));
            }
            Vector originalRow2 = matrix2.getVector(k);
            for (int n2 = 0; n2 < matrix2.getColumnCount(); n2++) {
                joinedRow.setC(i++, originalRow2.getC(n2));
            }
            joinedMatrix.setVector(k, joinedRow);
        }
        return joinedMatrix;
    }

}
