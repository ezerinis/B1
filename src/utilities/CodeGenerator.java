package utilities;

import structures.Matrix;
import structures.Vector;

public class CodeGenerator {

    VectorOperations vo = new VectorOperations();

    // Generuoja kodo zodzius is generuojancios matricos
    // Paduodama generuojanti matrica ir modulis 'q'
    // Grazinamas vektoriu, kurie yra visi kodo zodziai, masyvas
    public Vector[] generate(Matrix gMatrix, int q) throws Exception {
        int codeSize = (int) Math.pow(gMatrix.getRowCount(), q);
        Vector[] code = new Vector[codeSize];

        Vector gVector = new Vector(gMatrix.getRowCount());
        for (int i = 0; i < codeSize; i++) {
            code[i] = vo.multiply(gMatrix, gVector, q);
            vo.incrementVector(gVector, q);
        }

        return code;
    }
}
