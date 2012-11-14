package direct_method;

import structures.Vector;

public class DirectMethodCalculator {

    // Apskaiciuoja svoriu skirstini tiesioginio perrinkimo budu
    // Paduodamas kodas - vektoriu masyvas, grazinamas svoriu skirstinys
    public int[] calculateDistribution(Vector[] code) throws Exception {
        if (code.length == 0) {
            throw new Exception("Kodas tuscias");
        }
        int dimension = code[0].getSize();
        int[] distribution = new int[dimension + 1];
        for (Vector word : code) {
            int weight = calculateWeight(word);
            distribution[weight]++;
        }
        return distribution;
    }

    // Apskaiciuoja vieno zodzio svori
    // Paduodamas kodo zodis - vektorius, grazinamas svoris - sveikas skaicius
    private int calculateWeight(Vector word) {
        int weight = 0;
        for (int i = 0; i < word.getSize(); i++) {
            if (word.getC(i) > 0) {
                weight++;
            }
        }
        return weight;
    }
}
