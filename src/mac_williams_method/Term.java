package mac_williams_method;

public class Term {

    int cof;
    int pow;

    // Termo sukurimas, paduodant jo koeficienta ir laipsni
    public Term(int cof, int pow) {
        this.cof = cof;
        this.pow = pow;
    }


    // Termo klonavimas - grazina nauja terma, su tokiais paciais atributais
    @Override
    public Term clone() {
        return new Term(this.cof, this.pow);
    }

}
