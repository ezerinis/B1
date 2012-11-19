package structures;

public class Vector {

    private int[] data;

    // Vektoriaus sukurimas, paduodant masyva su vektoriaus koordinatemis ir moduli 'q'
    public Vector(int[] input, int q) throws Exception {
        if (input == null || input.length == 0) {
            throw new Exception("Vektorius negali buti tuscias");
        }
        data = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            if (input[i] >= q) {
                throw new Exception("Vektoriaus koordinate negali buti didesne arba lygi 'q'");
            }
            data[i] = input[i];
        }
    }

    // Nulinio vektoriaus sukurimas, paduodant vektoriaus ilgi
    public Vector(int length) {
        data = new int[length];
    }

    // Vektoriaus klonavimas - grazina nauja vektoriu, su tokiomis paciomis koordinatemis
    @Override
    public Vector clone() {
        Vector clonedVector = new Vector(data.length);
        for (int i = 0; i < data.length; i++) {
            clonedVector.setC(i, data[i]);
        }
        return clonedVector;
    }

    public int getC(int c) {
        return data[c];
    }

    public void setC(int c, int value) {
        data[c] = value;
    }

    public int getSize() {
        return data.length;
    }

    public int[] toArray() {
        return data.clone();
    }

    /*
    // Vektoriaus sukurimas, paduodant eilutine vektoriaus reprezentacija ir moduli 'q'
    public Vector(String input, int q) throws Exception {
        if (input == null || input.equals("")) {
            throw new Exception("Vektorius negali buti tuscias");
        }
        if (!isPrime(q)) {
            throw new Exception("'q' privalo buti pirminis");
        }
        int digitCount = String.valueOf(q).length();
        if (input.length() % digitCount != 0) {
            throw new Exception("Netinkamas vektoriaus ilgis");
        }
        int length = input.length() / digitCount;
        int number = Integer.parseInt(input);
        int pos = (int) Math.pow(10, digitCount);

        data = new int[length];
        for (int i = 0; i < length; i++) {
            int coord = number % pos;
            number = number / pos;
            if (coord >= q) {
                throw new Exception("Vektoriaus koordinate negali buti didesne arba lygi 'q'");
            }
            data[length - i - 1] = coord;
        }
    }
    */
}
