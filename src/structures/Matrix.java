package structures;

public class Matrix {

    private Vector[] data;

    // Matricos sukurimas, paduodant lentele ir moduli 'q'
    // Matricos eilutes sudaro vektoriai
    public Matrix(int[][] input, int q) throws Exception {
        if (input == null || input.length == 0) {
            throw new Exception("Matrica negali buti tuscia");
        }
        data = new Vector[input.length];
        int columnCount = input[0].length;
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
        return data[num].clone();
    }

    public int getRowCount() {
        return data.length;
    }

    public int getColumnCount() {
        if (data.length != 0 && data[0] != null) {
            return data[0].getSize();
        } else {
            return 0;
        }
    }

    public void print() {
        for (int k = 0; k < data.length; k++) {
            Vector row = data[k];
            for (int n = 0; n < row.getSize(); n++) {
                System.out.print(row.getC(n));
            }
            System.out.println();
        }
    }
}
