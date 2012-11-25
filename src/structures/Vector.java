package structures;

public class Vector {

    // Visa informacija laikoma sveiku skaiciu masyve
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

    // Vektoriaus sukurimas, paduodant simboliu eilute ir moduli 'q'
    public Vector(String input, int q) throws Exception {
        if (input == null || input.equals("")) {
            throw new Exception("Vektorius negali buti tuscias");
        }
        String[] coordinates = input.split("\\s+");
        data = new int[coordinates.length];
        for (int i = 0; i < coordinates.length; i++) {
            int coord = Integer.parseInt(coordinates[i].trim());
            if (coord >= q) {
                throw new Exception("Vektoriaus koordinate negali buti didesne arba lygi 'q'");
            }
            data[i] = coord;
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

    // Grazina vektoriaus koordinate pagal nurodyta koordinates numeri
    public int getC(int c) {
        return data[c];
    }

    // I nurodyta pozicija iraso nauja vektoriaus koordinates reiksme
    public void setC(int c, int value) {
        data[c] = value;
    }

    // Grazina vektoriaus ilgi
    public int getSize() {
        return data.length;
    }

    // Grazina masyva su vektoriaus koordinatemis
    public int[] toArray() {
        return data.clone();
    }

    // Grazina vektoriaus simboliu eilutes (String) reprezentacija
    @Override
    public String toString() {
        String result = "";
        for (int coord : data) {
            result += coord + " ";
        }
        return result;
    }
}
