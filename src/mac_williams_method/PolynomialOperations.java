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

    // Pakelia polinoma nurodytu laipsniu
    // Paduodamas polinomas ir laipsnis, grazinamas pakeltas laipsniu polinomas
    public Polynomial raiseToPow(Polynomial input, int pow) {
        if (pow == 0) {
            return new Polynomial(new Term[]{new Term(1, 0)});
        }
        Polynomial result = input.clone();
        for (int i = 0; i < pow - 1; i++) {
            result = mul(result, input);
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

    // Padaugina polinoma is sveiko skaiciaus
    // Paduodamas polinomas ir sveikas skaicius, grazinamas padaugintas polinomas
    public Polynomial mul(Polynomial poly, long multiplier) {
        Polynomial result = new Polynomial();
        for (int i = 0; i < poly.size(); i++) {
            result.add(to.mul(poly.getTerm(i), multiplier));
        }
        return result;
    }

}
