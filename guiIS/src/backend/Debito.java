package backend;

import java.util.Date;

public class Debito {
    private Euro quantita;

    public Debito(Euro quantita){
        this.quantita=quantita;
    }

    void sommaDebito(Euro importo) throws Euro.OverflowEuroException {//todo gestisco qui l exception o propago?
        this.quantita.aggiungiImporto(importo);
    }

    void sottraiDebito(Euro importo) throws Euro.InsufficientFundsException {//todo gestisco qui l exception o propago?
        this.quantita.sottraiImporto(importo);
    }

    void pagamentoDebito(Euro importo,Date data){
        //TODO
    }

    void pagamentoDebito(Euro importo){
        pagamentoDebito(importo,new Date());
    }
}
