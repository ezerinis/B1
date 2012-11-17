package mac_williams_method;

public class TermOperations {

    // Sudedami du termai
    // Paduodami du termai, grazinamas vienas termas, kuris yra ju suma
    public Term add(Term term1, Term term2) throws Exception {
        // Tikrina ar termu laipsniai vienodi
        // Paduodami du laipsniai, jeigu jie nesutampa, metama klaida
        if (term1.pow != term2.pow) {
            throw new Exception("Nesutampa termu laipsniai");
        }
        return new Term(term1.cof + term2.cof, term1.pow);
    }

    // Sudauginami du termai
    // Paduodami du termai, grazinamas vienas termas, kuris yra ju sandauga
    public Term mul(Term term1, Term term2) {
        int cof = term1.cof * term2.cof;
        int pow = term1.pow + term2.pow;
        return new Term(cof, pow);
    }

    // Padalina terma is sveiko skaiciaus
    // Paduodamas termas ir sveikas skaicius, grazinamas padalintas termas
    public Term div(Term term, int denominator) {
        Term result = new Term(term.cof / denominator, term.pow);
        return result;
    }
}
