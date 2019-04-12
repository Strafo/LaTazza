package backend.businessLogicLayer;

import backend.dataAccessLayer.gatewaysPkg.receiverPkg.CassaDaoReceiver;
import backend.dataAccessLayer.mementoPkg.Memento;
import backend.dataAccessLayer.rowdatapkg.AbstractEntryDB;
import javafx.application.Application;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import utils.Euro;

import static java.util.Objects.requireNonNull;

public class Cassa extends AbstractEntryDB {

    private final static long saldoIniziale= 500;
    private Euro saldo;

    public Cassa(){
        saldo= new Euro(saldoIniziale);
    }
    public Cassa(Euro s){
        saldo=requireNonNull(s);
    }


    public Euro getCopySaldo(){
        return new Euro(saldo.getEuro(),saldo.getCentesimi());
    }

    public void incrementaSaldo(Euro euro){
        try {
            saldo.aggiungiImporto(euro);
        } catch (Euro.OverflowEuroException e) {
            e.printStackTrace();
        }
    }
    public boolean decrementaSaldo(Euro euro) throws Euro.InsufficientFundsException {
            if(Euro.compare(euro, saldo)==1) return false;
            saldo.sottraiImporto(euro);
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
