// Generuoja kodo zodzius is generuojancios matricos
// Grazinamas vektoriu, kurie yra visi kodo zodziai, masyvas

package utilities;

import javax.swing.SwingWorker;
import structures.Matrix;
import structures.Vector;

// Si klase paveldi 'SwingWorker' klase, todel jos grazinamas rezultatas - kodo zodziai, pasiekiami per funkcija 'get()'
public class CodeGenerator extends SwingWorker<Vector[], Void> {

    private VectorOperations vo = new VectorOperations();

    private Matrix gMatrix;
    private  int q;

    // Konstruktorius
    // Paduodama generuojanti matrica ir modulis 'q'
    public CodeGenerator(Matrix gMatrix, int q) throws Exception {
        this.gMatrix = gMatrix;
        this.q = q;
    }

    // Generuojami kodo zodziai, kurie pasiekiami per funkcija get(), kuri yra paveldeta is klases 'SwingWorker'
    @Override
    protected Vector[] doInBackground() throws Exception {
        int codeSize = (int) Math.pow(q, gMatrix.getRowCount());
        Vector[] code = new Vector[codeSize];

        // Kodo zodziai gaunami generuojant visus galimus vektorius, kuriu ilgis lygus matricos eiluciu skaiciui, ir dauginant juos is eneruojancios matricos
        Vector gVector = new Vector(gMatrix.getRowCount());
        for (int i = 0; i < codeSize; i++) {
            if (Thread.interrupted()) {
                throw new Exception();
            }
            // Generuojanti matrica dauginama is vektoriaus ir gautas kodo zodis dedamas i masyva
            code[i] = vo.multiply(gMatrix, gVector, q);
            // Generuojamas naujas vektorius, padidinant senaji vienetu
            vo.incrementVector(gVector, q);
        }

        return code;
    }
}
