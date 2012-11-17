package mac_williams_method;

public class TermOperations {

    // Sudedami du termai
    // Paduodami du termai, grazinamas vienas termas, kuris yra ju suma
    public Term add(Term term1, Term term2) throws Exception {
        // Tikrina ar termu laipsniai vienodi
        // Paduodami du laipsniai, jeigu jie nesutampa, metama klaida
        if (term1.getPow() != term2.getPow()) {
            throw new Exception("Nesutampa termu laipsniai");
        }
        return new Term(term1.getCof() + term2.getCof(), term1.getPow());
    }

    // Sudauginami du termai
    // Paduodami du termai, grazinamas vienas termas, kuris yra ju sandauga
    public Term mul(Term term1, Term term2) {
        int cof = term1.getCof() * term2.getCof();
        int pow = term1.getPow() + term2.getPow();
        return new Term(cof, pow);
    }

    // Padalina terma is sveiko skaiciaus
    // Paduodamas termas ir sveikas skaicius, grazinamas padalintas termas
    public Term div(Term term, int denominator) {
        Term result = new Term(term.getCof() / denominator, term.getPow());
        return result;
    }

    // Padaugina terma is sveiko skaiciaus
    // Paduodamas termas ir sveikas skaicius, grazinamas padaugintas termas
    public Term mul(Term term, int multiplier) {
        Term result = new Term(term.getCof() * multiplier, term.getPow());
        return result;
    }
}
