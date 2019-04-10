package backend.businessLogicLayer;
import backend.dataAccessLayer.mementoPkg.Memento;
import backend.dataAccessLayer.rowdatapkg.AbstractEntryDB;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import utils.Euro;

import java.util.Objects;

public class Debito extends AbstractEntryDB {
    private Euro quantita;

    public Debito(Euro quantita){
        this.quantita=Objects.requireNonNull(quantita);
    }

    public void sommaDebito(Euro importo) throws Euro.OverflowEuroException {
        this.quantita.aggiungiImporto(importo);
    }

    public Euro getImporto(){
        return new Euro(quantita);
    }

    /**
     *
     * @param importo
     * @return il resto importo-debito
     */
    private Euro sottraiDebito(Euro importo){
        Euro res;
        if (Euro.compare(importo, quantita) >= 0) {
            res=importo.sottraiImporto(quantita);//torna il resto
            quantita=new Euro(0,0);
            return res;
        } else {
            quantita.sottraiImporto(importo);
            return new Euro(0, 0);
        }

    }



    public Euro pagamentoDebito(Personale pers,Euro importo){
        return sottraiDebito(importo);
    }

    @Override
    public Memento createMemento() {
        throw  new UnsupportedOperationException();
    }

    @Override
    public void undoChanges() {
        throw  new UnsupportedOperationException();
    }
}
