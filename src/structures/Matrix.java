package structures;

public class Matrix {

    private Vector[] data;

    // Matricos sukurimas, paduodant lentele ir moduli 'q'
    // Matricos eilutes sudaro vektoriai
    public Matrix(int[][] input, int q) throws Exception {
        if (input == null || input.length == 0) {
            throw new Exception("Matrica negali buti tuscia");
        }
        if (input.length > input[0].length) {
            throw new Exception("Matricos eiluciu skaicius 'k' negali buti didesnis uz stulpeliu skaiciu 'n'");
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

    // Matricos sukurimas, paduodant simboliu eilute ir moduli 'q'
    public Matrix(String input, int q) throws Exception {
        if (input == null || input.equals("")) {
            throw new Exception("Matrica negali buti tuscia");
        }
        String[] rows = input.split("\n");
        Vector[] temp = new Vector[rows.length];
        int count = 0;
        for (String row : rows) {
            if (!row.trim().equals("")) {
                temp[count++] = new Vector(row, q);
            }
        }
        int size = temp[0].getSize();
        for (int i = 0; i < count; i++) {
            if (temp[i].getSize() != size) {
                throw new Exception("Matricos eiluciu ilgiai nesutampa");
            }
        }
        if (temp.length > temp[0].getSize()) {
            throw new Exception("Matricos eiluciu skaicius 'k' negali buti didesnis uz stulpeliu skaiciu 'n'");
        }
        data = new Vector[count];
        System.arraycopy(temp, 0, data, 0, count);
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

    @Override
    public Matrix clone() {
        Matrix clonedMatrix = new Matrix(data.length);
        for (int i = 0; i < data.length; i++) {
            clonedMatrix.setVector(i, data[i].clone());
        }
        return clonedMatrix;
    }

    public int[][] toArray() {
        int[][] array = new int[data.length][data[0].getSize()];
        for (int k = 0; k < data.length; k++) {
            Vector row = data[k];
            array[k] = row.toArray();
        }
        return array;
    }

    @Override
    public String toString() {
        String result = "";
        for (int k = 0; k < data.length; k++) {
            result += data[k].toString() + "\n";

        }
        return result;
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

    // Perstata - perstato matricos stulpelius
    // Paduodami stulpeliu numeriai, matricos, kuriai iskviecima si funkcija, stulpeliai perstatomi
    public void rearrange(int i, int j) {
        for (Vector vector : data) {
            int temp = vector.getC(i);
            vector.setC(i, vector.getC(j));
            vector.setC(j, temp);
        }
    }
}
