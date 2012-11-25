// Si klase skaiciuoja kodo zodziu svoriu skirstini tiesioginio perrinkimo budu

package direct_method;

import structures.Vector;

public class DirectMethodCalculator {

    // Paduodamas tiesinis abeceles kodas - vektoriu masyvas, grazinamas svoriu skirstinys
    public long[] calculateDistribution(Vector[] code) throws Exception {
        if (code.length == 0) {
            throw new Exception("Kodas tuscias");
        }
        int k = code[0].getSize();
        long[] distribution = new long[k + 1];
        for (Vector word : code) {
            int weight = calculateWeight(word);
            distribution[weight]++;
        }
        return distribution;
    }

    // Apskaiciuoja vieno zodzio svori
    // Paduodamas kodo zodis, grazinamas nenuliniu jo koordinaciu skaicius
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
