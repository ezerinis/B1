package mac_williams_method;

public class Term {

    private long cof;
    private int pow;

    // Termo sukurimas, paduodant jo koeficienta ir laipsni
    public Term(long cof, int pow) {
        this.cof = cof;
        this.pow = pow;
    }

    // Termo klonavimas - grazina nauja terma, su tokiais paciais atributais
    @Override
    public Term clone() {
        return new Term(this.cof, this.pow);
    }

    // Grazinamas termo koeficientas
    public long getCof() {
        return cof;
    }

    // Grazinamas termo laipsnis
    public int getPow() {
        return pow;
    }

    // Termo koeficientas pakeiciamas i nauja, kuris paduodamas kaip parametras
    public void setCof(long cof) {
        this.cof = cof;
    }

    // Termo laipsnis pakeiciamas i nauja, kuris paduodamas kaip parametras
    public void setPow(int pow) {
        this.pow = pow;
    }

}
