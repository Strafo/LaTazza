package backend.memento;

import backend.daopkg.rowdatapkg.CialdeEntry;
import backend.movimentopkg.MovimentoVendita;

public class MementoMovimentoVendita extends MementoMovimento implements Memento {

    private  int quantita;
    private CialdeEntry tipo;
    private boolean contanti;

    @Override
    public <T> void setMementoState(T originator) {
        super.setMementoState(originator);
        this.quantita=((MovimentoVendita)originator).getQuantita();
        this.tipo=((MovimentoVendita)originator).getTipo();
        this.contanti=((MovimentoVendita)originator).isContanti();
    }

    @Override
    public  MovimentoVendita getMementoState(){
        return new MovimentoVendita(data,cliente,quantita,tipo,contanti);
    }
}
