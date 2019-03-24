package backend;
import backend.clientpkg.Personale;
import backend.movimentopkg.MovimentoDebito;
import java.util.Date;
import java.util.Objects;

public class Debito {
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

    /* Ormai inutile
    public Euro pagamentoDebito(Personale pers, Euro importo, Date data)  {


        Euro resto=this.sottraiDebito(importo);//todo queste operazioni devono essere eseguite o tutte o  nessuna (gestire quindi le possibili eccezioni ecc...)
       MovimentoDebito movimentoDebito=new MovimentoDebito(data,pers,importo);
        movimentoDebito.aggiornaDebito();//todo fino a qui


        return resto;
    }

*/
    public Euro pagamentoDebito(Personale pers,Euro importo){
        return sottraiDebito(importo);
    }
}
