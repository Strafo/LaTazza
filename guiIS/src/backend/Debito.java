package backend;

import java.util.Date;
import java.util.Objects;

public class Debito {
    private Euro quantita;

    public Debito(Euro quantita){
        this.quantita=Objects.requireNonNull(quantita);
    }

    void sommaDebito(Euro importo) throws Euro.OverflowEuroException {
        this.quantita.aggiungiImporto(importo);
    }



    Euro sottraiDebito(Euro importo){
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

    Euro pagamentoDebito(Personale pers,Euro importo,Date data) throws Euro.InsufficientFundsException {
        Euro resto=importo.sottraiImporto(quantita);
        MovimentoDebito movimentoDebito=new MovimentoDebito(data,pers,importo);
        movimentoDebito.aggiornaDebito();
        return resto;
    }

    Euro pagamentoDebito(Personale pers,Euro importo){
        return pagamentoDebito(pers,importo,new Date());
    }
}
