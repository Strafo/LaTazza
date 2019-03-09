package backend;

public class Personale extends Cliente {


    private Debito debito;


    public Debito getDebito() {
        return debito;
    }

    public void setDebito(Debito debito) {
        this.debito = debito;
    }

    public Personale(String nome, String cognome) {
        super(nome, cognome);
    }

}
