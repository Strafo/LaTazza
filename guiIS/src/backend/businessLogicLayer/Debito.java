package backend.businessLogicLayer;
import backend.dataAccessLayer.mementoPkg.Memento;
import backend.dataAccessLayer.rowdatapkg.AbstractEntryDB;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import utils.Euro;

import java.util.Objects;

public class Debito  {
    private Euro quantita;

    public Debito(Euro quantita){
        this.quantita=Objects.requireNonNull(quantita);
    }

    public void sommaDebito(Euro importo) throws Euro.OverflowEuroException,NullPointerException {

        this.quantita.aggiungiImporto(importo);
    }

    public Euro getImporto(){
        return new Euro(quantita);
    }

    /**
     *
     * @param importo
     * @return se importo>quantita l'operazione viene abortita e ritorna false, altrimenti true
     */
    private boolean sottraiDebito(Euro importo) throws NullPointerException{
        if (Euro.compare(importo, quantita) > 0) return false;
        quantita.sottraiImporto(importo);
        return true;

    }



    public boolean pagamentoDebito(Personale pers,Euro importo) throws NullPointerException{
        return sottraiDebito(importo);
    }

}
