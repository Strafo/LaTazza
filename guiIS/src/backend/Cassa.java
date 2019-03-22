package backend;

import static java.util.Objects.requireNonNull;

public class Cassa {

    private final static long saldoIniziale= 500;
    private Euro saldo;

    public Cassa(){
        saldo= new Euro(saldoIniziale);
    }

    public Cassa(Euro s){
        saldo=requireNonNull(s);
    }

    public Euro getSaldo(){
        return saldo;
    }

    public void incrementaSaldo(Euro euro){
        try {
            saldo.aggiungiImporto(euro);
        } catch (Euro.OverflowEuroException e) {
            e.printStackTrace();
        }
    }

    public void decrementaSaldo(Euro euro) throws Euro.InsufficientFundsException {
            saldo.sottraiImporto(euro);
    }



}
