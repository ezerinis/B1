package mac_williams_method;

public class PolynomialOperations {

    TermOperations to = new TermOperations();

    // Sudeda du polinomus
    // Paduodami du polinomai, grazinamas vienas polinomas, kuris yra ju suma
    public Polynomial add(Polynomial poly1, Polynomial poly2) {
        Polynomial result = poly1.clone();
        for (int i = 0; i < poly2.size(); i++) {
            result.add(poly2.getTerm(i));
        }
        result.normalize();
        return result;
    }

    // Pakelia du termus nurodytu laipsniu
    // Paduodami du termai ir laipsnis, grazinamas polinomas
    public Polynomial raiseToPow(Term term1, Term term2, int pow) {
        Polynomial basePoly = new Polynomial(new Term[]{term1, term2});
        Polynomial result = basePoly.clone();
        for (int i = 0; i < pow - 1; i++) {
            result = mul(result, basePoly);
        }
        return result;
    }

    // Sudaugina du polinomus
    // Paduodami du polinomai, grazinamas sudaugintas polinomas
    public Polynomial mul(Polynomial poly1, Polynomial poly2) {
        Polynomial result = new Polynomial();
        for (int i = 0; i < poly1.size(); i++) {
            Term term = poly1.getTerm(i);
            for (int j = 0; j < poly2.size(); j++) {
                result.add(to.mul(term, poly2.getTerm(j)));
            }
        }
        result.normalize();
        return result;
    }

    // Padalina polinoma is sveiko skaiciaus
    // Paduodamas polinomas ir sveikas skaicius, grazinamas padalintas polinomas
    public Polynomial div(Polynomial poly, int denominator) {
        Polynomial result = new Polynomial();
        for (int i = 0; i < poly.size(); i++) {
            result.add(to.div(poly.getTerm(i), denominator));
        }
        return result;
    }

}
