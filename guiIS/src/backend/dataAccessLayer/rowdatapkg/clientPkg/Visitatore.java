package backend.dataAccessLayer.rowdatapkg.clientPkg;

import backend.dataAccessLayer.mementoPkg.Memento;
import backend.dataAccessLayer.mementoPkg.MementoVisitatore;

public final class Visitatore extends Cliente {

    public Visitatore(String nome, String cognome) {
        super(nome, cognome);
    }

    public Visitatore(){}

    @Override
    public String toString(){
        return super.toString()+"(Visitatore)";
    }

    @Override
    public Memento createMemento() {
        return new MementoVisitatore();
    }

    @Override
    public void undoChanges(){
        super.undoChanges();
        removeMemento();
    }
}
