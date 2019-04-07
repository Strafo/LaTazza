package backend.dataAccessLayer.rowdatapkg.movimentoPkg;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Cliente;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.dataAccessLayer.mementoPkg.Memento;
import backend.dataAccessLayer.mementoPkg.MementoMovimentoVendita;

import java.sql.Timestamp;
import java.util.Objects;

public final class MovimentoVendita extends Movimento {
    private  int quantita;
    private CialdeEntry tipo;
    private boolean contanti;

    public MovimentoVendita(Timestamp data, Cliente cliente, int quantita, CialdeEntry tipo, boolean contanti) throws IllegalArgumentException {
        super(data, cliente);

        this.tipo=Objects.requireNonNull(tipo);
        if(quantita<=0){
            throw new IllegalArgumentException("quantità negativa o uguale a zero");
        }
        this.quantita=quantita;
        this.contanti=contanti;
    }

    public MovimentoVendita(){}

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita){
        setMementoIfNotDef();
        this.quantita=quantita;
    }

    public void setTipo(CialdeEntry tipo) {
        setMementoIfNotDef();
        this.tipo = tipo;
    }

    public void setContanti(boolean contanti) {
        setMementoIfNotDef();
        this.contanti = contanti;
    }

    public CialdeEntry getTipo() {
        return tipo;
    }

    public boolean isContanti() { return contanti;}

    @Override
    public String toString() {
        return super.toString()+
                "  quantità:"+quantita+" tipo:"+tipo.getTipo()+" contanti:"+contanti+" (MovimentoVendita)";
    }

    @Override
    public Memento createMemento() {
        return new MementoMovimentoVendita();
    }

    @Override
    public void undoChanges(){
        super.undoChanges();
        MovimentoVendita oldState=(MovimentoVendita) this.getMemento().getMementoState();
        this.quantita=oldState.getQuantita();
        this.contanti=oldState.isContanti();
        this.tipo=oldState.getTipo();
        removeMemento();
    }

}
