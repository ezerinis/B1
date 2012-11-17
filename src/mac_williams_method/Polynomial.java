package mac_williams_method;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Polynomial {

    private List<Term> terms = new LinkedList<>();

    // Tuscio polinomo sukurimas
    public Polynomial() {
    }

    // Sukuria nauja polinoma paduodant termu masyva
    // Laikoma, kad visi esantys termai polinome yra susieti sudeties operacijomis
    public Polynomial(Term[] input) {
        terms.addAll(Arrays.asList(input));
    }

    // Sukuria nauja polinoma paduodant termu sarasa
    // Laikoma, kad visi esantys termai polinome yra susieti sudeties operacijomis
    public Polynomial(List<Term> input) {
        terms.addAll(input);
    }

    // Polinomo klonavimas - grazina nauja polinoma, sudaryta is tokiu paciu termu
    @Override
    public Polynomial clone() {
        return new Polynomial(terms);
    }

    // Grazina polinomo terma, kurio pozicija sutampa su pateiktu indeksu
    public Term getTerm(int index) {
        return terms.get(index).clone();
    }

    // Prijungia terma prie polinomo termu saraso galo
    public void add(Term term) {
        terms.add(term);
    }

    // Grazina polinomo dydi, kuris lygus jo termu skaiciui
    public int size() {
        return terms.size();
    }

    // Normalizuoja polinoma - isrikiuoja termus laipsniu didejimo tvarka ir sudeda visus termus su vienodais laipsniais
    // Todel polinome negali buti termu, susietu daugybos operacijomis. Tokiu atveju normalizuojant polinoma bus gautas nekorektiskas rezultatas
    public void normalize() {
        if (terms.isEmpty() || terms.size() == 1) {
            return;
        }

        // Termai burbulo metodu isrikiuojami didejimo tvarka
        for (int i = 0; i < terms.size() - 1; i++) {
            for (int j = 0; j < terms.size() - 1; j++) {
                Term term1 = terms.get(j);
                Term term2 = terms.get(j + 1);
                if (term1.pow > term2.pow) {
                    Term temp = term1;
                    terms.set(j, term2);
                    terms.set(j + 1, temp);
                }
            }
        }

        // Termai su vienodais laipsniais sudedami
        List normalizedPoly = new LinkedList<>();
        Term newTerm = terms.get(0);
        for (int i = 1; i < terms.size(); i++) {
            if (newTerm.pow == terms.get(i).pow) {
                newTerm.cof += terms.get(i).cof;
            } else {
                normalizedPoly.add(newTerm);
                newTerm = terms.get(i);
            }
        }
        normalizedPoly.add(newTerm);
        terms = normalizedPoly;
    }
}
