package structures;

public class Matrix {

    private Vector[] data;
    private int columnCount;

    // Matricos sukurimas, paduodant lentele ir moduli 'q'
    // Matricos eilutes sudaro vektoriai
    public Matrix(int[][] input, int q) throws Exception {
        if (input == null || input.length == 0) {
            throw new Exception("Matrica negali buti tuscia");
        }
        data = new Vector[input.length];
        columnCount = input[0].length;
        for (int i = 0; i < input.length; i++) {
            if (input[i].length != columnCount) {
                throw new Exception("Matricos eiluciu ilgiai nesutampa");
            }
            data[i] = new Vector(input[i], q);
        }
    }

    // Tuscios matricos sukurimas, paduodant tik eiluciu skaiciu
    public Matrix(int rowCount) {
        data = new Vector[rowCount];
    }

    public void setVector(int num, Vector vector) {
        data[num] = vector;
    }

    public Vector getVector(int num) {
        return data[num];
    }

    public int getRowCount() {
        return data.length;
    }

    public Vector[] getData() {
        return data;
    }

    public int getColumnCount() {
        return columnCount;
    }
}
