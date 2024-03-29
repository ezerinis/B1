// Si klase skaiciuoja kodo zodziu svoriu skirstini pritaikant MacWilliams tapatybe

package mac_williams_method;

public class MacWilliamsMethodCalculator {

    // Reiksme, kuri istatoma vietoj x, formuleje: 1/|L|*omega(x+(q-1)y, x-y)
    private final int X = 1;

    private PolynomialOperations po = new PolynomialOperations();

    // Apskaiciuoja svoriu skirstini panaudojant McWilliams tapatybe
    // Paduodamas dualaus kodo zodziu svoriu skirstinys, dualaus kodo vektoriaus ilgis ir modulis 'q'
    // Grazinamas kodo zodziu svoriu skirstinys
    public long[] calculateDistribution(long[] dualWeightDistribution, int n, int q) {

        // Susumuojant dualaus kodo skirstini, apskaiciuojamas dualaus kodo dydis
        int codeSize = 0;
        for (long weight : dualWeightDistribution) {
            codeSize += weight;
        }

        // Pirmasis funkcijos parametras: x + (q - 1) * y
        Polynomial poly1 = new Polynomial(new Term[]{new Term(X, 0), new Term(q - 1, 1)});

        // Antrasis funkcijos parametras: x - y
        Polynomial poly2 = new Polynomial(new Term[]{new Term(X, 0), new Term(-1, 1)});

        // Generuojami visi 'Ai * x^(n-i) * y^i', pakeliami laipsniu ir sudauginami
        // Gauti rezultatai dedami i masyva
        Polynomial[] polyParts = new Polynomial[n + 1];
        for (int i = 0; i < n + 1; i++) {
            Polynomial temp = po.mul(po.raiseToPow(poly1, n - i), po.raiseToPow(poly2, i));
            polyParts[i] = po.mul(temp, dualWeightDistribution[i]);
        }

        // Anksciau gautos polinomo dalys sudedamos
        Polynomial fullPoly = polyParts[0];
        for (int i = 1; i < polyParts.length; i++) {
            fullPoly = po.add(fullPoly, polyParts[i]);
        }

        // Gautas polinomas padalinamas is kodo dydzio
        Polynomial finalPoly = po.div(fullPoly, codeSize);

        // Koeficientai, esantys prie gauto polinomo termu, yra kodo zodziu svoriu skirstinys
        // Jie atrenkami ir sudedami i masyva
        long[] distribution = new long[n + 1];
        for (int i = 0; i < finalPoly.size(); i++) {
            Term term = finalPoly.getTerm(i);
            distribution[term.getPow()] = term.getCof();
        }

        return distribution;
    }
}
