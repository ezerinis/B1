package utilities;

import structures.Matrix;
import structures.Vector;

public class VectorOperations {

    // Sudaugina matrica su vektoriumi moduliu 'q', grazina vektoriu
    public Vector multiply(Matrix matrix, Vector vector, int q) throws Exception {
        if (matrix.getRowCount() != vector.getSize()) {
            throw new Exception("Negalima sudauginti, kai matricos eiluciu skaicius ir vektoriaus ilgis nesutampa");
        }

        // Is pradziu matricos eilutes sudauginamos su vektoriaus atitinkamomis koordinatemis moduliu 'q'
        Matrix multipliedMatrix = new Matrix(matrix.getRowCount());
        for (int i = 0; i < vector.getSize(); i++) {
            Vector multipliedVector = multiply(vector.getC(i), matrix.getVector(i), q);
            multipliedMatrix.setVector(i, multipliedVector);
        }

        // Sudaugintos matricos eilutes sudedamos vienos su kitomis modulu 'q'
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

    // Prie kiekvienos vektoriaus koordinates prideda sveika skaiciu moduliu 'q', grazina vektoriu
    public Vector add(int num, Vector initialVector, int q) throws Exception {
        Vector result = new Vector(initialVector.getSize());
        for (int i = 0; i < initialVector.getSize(); i++) {
            int value = (num + initialVector.getC(i)) % q;
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
    // Paduodami du vektoriai ir modulis 'q', grazinamas vektorius
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

            // Jeigu vektoriaus koordinate, padidinus ja vienetu, netampa lygi nuliui, grazinamas gautas vektorius
            // Priesingu atveju veiksmai vykdomi toliau ir vienetu didinama jau sekanti vektoriaus koordinate
            if (value != 0) {
                return;
            }
        }
    }
}
