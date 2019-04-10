package backend.businessLogicLayer;

import backend.dataAccessLayer.mementoPkg.Memento;
import backend.dataAccessLayer.rowdatapkg.AbstractEntryDB;
import utils.Euro;

import static java.util.Objects.requireNonNull;

public class Cassa extends AbstractEntryDB {

    private final static long saldoIniziale= 500;
    private Euro saldo;

    public Cassa(){
        saldo= new Euro(saldoIniziale);
    }

    @Override
    public Memento createMemento() {
        return null;
    }

    @Override
    public void undoChanges() {

    }

    public Cassa(Euro s){
        saldo=requireNonNull(s);
    }

    public Euro getCopySaldo(){
        return new Euro(saldo);
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
