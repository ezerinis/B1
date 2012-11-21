package utilities;

import javax.swing.SwingWorker;
import structures.Matrix;
import structures.Vector;

public class CodeGenerator extends SwingWorker<Vector[], Void> {

    private VectorOperations vo = new VectorOperations();

    private Matrix gMatrix;
    private  int q;

    // Generuoja kodo zodzius is generuojancios matricos
    // Paduodama generuojanti matrica ir modulis 'q'
    // Grazinamas vektoriu, kurie yra visi kodo zodziai, masyvas
    public CodeGenerator(Matrix gMatrix, int q) throws Exception {
        this.gMatrix = gMatrix;
        this.q = q;
    }

    @Override
    protected Vector[] doInBackground() throws Exception {
        int codeSize = (int) Math.pow(q, gMatrix.getRowCount());
        Vector[] code = new Vector[codeSize];

        Vector gVector = new Vector(gMatrix.getRowCount());
        for (int i = 0; i < codeSize; i++) {
            if (Thread.interrupted()) {
                throw new Exception();
            }
            code[i] = vo.multiply(gMatrix, gVector, q);
            vo.incrementVector(gVector, q);
        }

        return code;
    }
}
