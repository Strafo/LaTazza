package backend.businessLogicLayer;

import backend.dataAccessLayer.mementoPkg.Memento;
import backend.dataAccessLayer.rowdatapkg.AbstractEntryDB;
import utils.Euro;
import static java.util.Objects.requireNonNull;

public class Cassa extends AbstractEntryDB {

    private final static long saldoIniziale= 500;
    private Euro saldo;

    Cassa(){
        saldo= new Euro(saldoIniziale);
    }//todo dove viene utilizzato?
    public Cassa(Euro s){
        saldo=requireNonNull(s);
    }


    Euro getCopySaldo(){
        return new Euro(saldo.getEuro(),saldo.getCentesimi());
    }

    void incrementaSaldo(Euro euro)throws Euro.OverflowEuroException,NullPointerException {
        saldo.aggiungiImporto(euro);
    }

    boolean decrementaSaldo(Euro euro) throws NullPointerException {
        try{
            saldo.sottraiImporto(euro);
        }catch (Euro.InsufficientFundsException e){
            return false;
        }
        return true;
    }


    @Override
    public Memento createMemento() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void undoChanges() {
        throw  new UnsupportedOperationException();
    }


}
