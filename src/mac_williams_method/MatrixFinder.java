package mac_williams_method;

import structures.Matrix;
import structures.Vector;
import utilities.MatrixGenerator;
import utilities.MatrixOperations;
import utilities.VectorOperations;

public class MatrixFinder {

    private MatrixGenerator mg = new MatrixGenerator();
    private MatrixOperations mo = new MatrixOperations();
    private VectorOperations vo = new VectorOperations();

    // Suranda kontroline matrica
    // Paduodama generuojanti matrica ir modulis 'q', grazinama kontroline matrica
    public Matrix findControlMatrix(Matrix gMatrix, int q) throws Exception {
        if (gMatrix.getRowCount() == gMatrix.getColumnCount()) {
            throw new Exception("Pateikta generuojanti matrica yra kvadratine, todel negalima rasti jos kontrolines matricos");
        }

        // Randama standartine paduotos matricos forma
        Matrix standardMatrix = findStandardMatrix(gMatrix, q);

        // Nuo generuojancios matricos atskiriama kaireje jos puseje esanti standartinio pavidalo vienetine matrica
        // Gaunama nauja mazesne matrica, kuri turi maziau stulpeliu ir yra sudaryta is generuojancios matricos desiniosios dalies
        Matrix tempMatrix = mo.split(standardMatrix, standardMatrix.getRowCount());

        // Gauta matrica transponuojama
        tempMatrix = mo.transpose(tempMatrix);

        // Transponuotos matricos reiksmes paverciamos neigiamomis ir sutvarkomos pagal moduli 'q'
        tempMatrix = mo.makeNegative(tempMatrix, q);

        // Sukuriama vienetine matrica
        int sideLength = standardMatrix.getColumnCount() - standardMatrix.getRowCount();
        Matrix unitaryMatrix = mg.generateUnitaryMatrix(sideLength, sideLength);

        // Apdorota transponuotoji matrica sujungiama su vienetine matrica
        // Gaunama kontroline matrica
        Matrix controlMatrix = mo.join(tempMatrix, unitaryMatrix);

        return controlMatrix;
    }

    // Suveda matrica i standartine jos forma
    // Paduodama matrica ir modulis 'q', grazinama standartine matrica
    public Matrix findStandardMatrix(Matrix gMatrix, int q) throws Exception {
        Matrix standardMatrix = gMatrix.clone();
        int rowCount = standardMatrix.getRowCount();

        // Pereinama kiekviena eilute
        for (int k = 0; k < rowCount;) {
            Vector row = standardMatrix.getVector(k);
            // Jei eilutes reiksme, esanti k-oje eilutes pozicijoje nelygi 0, visa eilute padauginama is tokio skaiciaus, kad ta reiksme taptu lygi 1
            if (row.getC(k) != 0) {
                // Randama atvirkstine reiksme, is kurios padauginus skaiciu jis tampa lygus 1
                int inverse = findInverse(row.getC(k), q);
                // Eilute padauginama is rastos reiksmes
                row = vo.multiply(inverse, row, q);
                standardMatrix.setVector(k, row);

                // Prie kiekvienos matricos eilutes pridedama k-oji eilute, padauginta is tam tikro skaiciaus
                for (int i = 0; i < rowCount; i++) {
                    if (i == k) {
                        continue;
                    }

                    // Randamas skaicius, is kurio reikia padauginti k-aja eilute, kad ja pridejus prie i-osios eilutes i-osios eilutes k-oji reiksme taptu lygi 0
                    int multiplier = q - standardMatrix.getVector(i).getC(k);
                    // k-oji eilute padauginama is rasto skaiciaus
                    Vector multipliedRow = vo.multiply(multiplier, row, q);
                    // Padauginta eilute sudedama su i-aja eilute, i-osios eilutes k-oji reiksme tampa lygi 0
                    Vector tempRow = vo.add(standardMatrix.getVector(i), multipliedRow, q);
                    standardMatrix.setVector(i, tempRow.clone());
                }
                k++;

            // Jei k-osios eilutes k-oji reiksme lygi nuliui, reikia perstatyti eilutes arba stulpelius taip, kad toje pozicijoje esanti reiksme nebutu lygi 0
            } else {
                boolean found = false;
                int i = k + 1;
                while (!found && i < rowCount) {
                    // Jeigu randama tokia eilute, kurios k-ojoje pozicijoje esanti reiksme nelygi 0, ji sukeiciama su k-aja eilute
                    if (standardMatrix.getVector(i).getC(k) != 0) {
                        Vector temp = standardMatrix.getVector(i);
                        standardMatrix.setVector(i, row.clone());
                        standardMatrix.setVector(k, temp);
                        found = true;
                    } else {
                        i++;
                    }
                }

                // Jeigu nebuvo rasta tinkama eilute, ieskoma tinkamo stulpelio, su kuriuo butu galima sukeisti k-aji stulpeli
                i = k + 1;
                while (!found && i < standardMatrix.getColumnCount()) {
                    if (standardMatrix.getVector(k).getC(i) != 0) {
                        // Jei randamas toks stulpelis, kurio k-oje eiluteje esanti reiksme nelygi 0, ivykdoma perstata su k-uoju stulpeliu
                        standardMatrix.rearrange(k, i);
                        found = true;
                    } else {
                        i++;
                    }
                }
                // Jeigu nebuvo rasta nei tinkama eilute, nei tinkamas stulpelis su kuriais butu buve galima sukeisti, metama klaida, kad negalima rasti matricos standartines formos
                if (!found) {
                    throw new Exception("Matrica neturi standartines formos");
                }
            }
        }
        return standardMatrix;
    }

    // Randa atvirkstini sveiko skaiciaus elementa
    // Paduodamas skaicius ir modulis 'q', grazinamas paduoto skaiciaus atvirkstinis elementas
    private int findInverse(int num, int q) {
        int inverse = 1;
        while (inverse < q) {
            if ((inverse * num) % q == 1) {
                return inverse;
            }
            inverse++;
        }
        return 0;
    }
}
