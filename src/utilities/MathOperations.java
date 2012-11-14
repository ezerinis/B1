package utilities;

import structures.Matrix;
import structures.Vector;

public class MathOperations {

    // Sudaugina matrica su vektoriumi moduliu 'q', grazina vektoriu
    public Vector multiply(Matrix matrix, Vector vector, int q) throws Exception {
        if (matrix.getRowCount() != vector.getSize()) {
            throw new Exception("Negalima sudauginti, kai matricos eiluciu skaicius ir vektoriaus ilgis nesutampa");
        }

        // Is pradziu matricos eilutes-vektoriai sudauginami su vektoriaus atitinkamomis koordinatemis moduliu 'q'
        Matrix multipliedMatrix = new Matrix(matrix.getRowCount());
        for (int i = 0; i < vector.getSize(); i++) {
            Vector multipliedVector = multiply(vector.getC(i), matrix.getVector(i), q);
            multipliedMatrix.setVector(i, multipliedVector);
        }

        // Sudaugintos matricos eilutes-vektoriai sudedami vieni su kitais modulu 'q'
        Vector result = new Vector(matrix.getColumnCount());
        for (int i = 0; i < multipliedMatrix.getRowCount(); i++) {
            result = add(result, multipliedMatrix.getVector(i), q);
        }
        return result;
    }

    // Sudaugina sveika skaiciu su vektoriumi moduliu 'q', grazina vektoriu
    public Vector multiply(int num, Vector initialVector, int q) throws Exception {
        Vector result = new Vector(initialVector.getSize());
        for (int i = 0; i < initialVector.getSize(); i++) {
            int value = (num * initialVector.getC(i)) % q;
            result.setC(i, value);
        }
        return result;
    }

    // Sudeda du vektorius moduliu 'q', grazina vektoriu
    public Vector add(Vector vector1, Vector vector2, int q) throws Exception {
        if (vector1.getSize() != vector2.getSize()) {
            throw new Exception("Negalima sudeti vektoriu, kuriu ilgiai nesutampa");
        }
        Vector result = new Vector(vector1.getSize());
        for (int i = 0; i < vector1.getSize(); i++) {
            int value = (vector1.getC(i) + vector2.getC(i)) % q;
            result.setC(i, value);
        }
        return result;
    }

    // Skaliarine dvieju vektoriu sandauga
    public int scalarMultiply(Vector vector1, Vector vector2, int q) throws Exception {
        if (vector1.getSize() != vector2.getSize()) {
            throw new Exception("Negalima skaliariskai sudauginti vektoriu, kuriu ilgiai nesutampa");
        }
        int result = 0;
        for (int i = 0; i < vector1.getSize(); i++) {
            int multiplication = (vector1.getC(i) * vector2.getC(i)) % q;
            result = (result + multiplication) % q;
        }
        return result;
    }

    // Vektorius padidinamas vienetu
    // Si funkcija naudojama generuojant visus imanomus vektorius
    public void incrementVector(Vector vector, int q) {
        for (int i = 0; i < vector.getSize(); i++) {
            int value = (vector.getC(i) + 1) % q;
            vector.setC(i, value);
            if (value != 0) {
                return;
            }
        }
    }

    // Tikrina ar skaicius pirminis
    public boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if ((num % i) == 0) {
                return false;
            }
        }
        return true;
   }
}
