package dataAccessLayer.rowdatapkg.movimentoPkg;
import utils.Euro;
import dataAccessLayer.mementoPkg.Memento;
import dataAccessLayer.mementoPkg.MementoMovimentoDebito;
import dataAccessLayer.rowdatapkg.clientPkg.Personale;

import java.sql.Timestamp;
import java.util.Objects;

public final class MovimentoDebito extends Movimento {
    private Euro importo;

    public MovimentoDebito(Timestamp data, Personale cliente, Euro importo) {
        super(data, cliente);
        this.importo=Objects.requireNonNull(importo);
    }

    public Euro getImporto() {
        return importo;
    }

    public void setImporto(Euro importo) {
        setMementoIfNotDef();
        this.importo = importo;
    }

    @Override
    public String toString() {
        return super.toString()+
                "  importo:"+importo.toString()+" (MovimentoDebito)";
    }

    @Override
    public Memento createMemento() {
        return new MementoMovimentoDebito();
    }

    @Override
    public void undoChanges(){
        super.undoChanges();
        MovimentoDebito oldState=(MovimentoDebito) this.getMemento().getMementoState();
        this.importo=oldState.getImporto();
        removeMemento();
    }

}
