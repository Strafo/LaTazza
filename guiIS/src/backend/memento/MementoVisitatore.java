package backend.memento;

import backend.clientpkg.Visitatore;

public class MementoVisitatore extends MementoCliente implements Memento {

    @Override
    public <T> void setMementoState(T originator) {
        super.setMementoState(originator);
    }

    @Override
    public Visitatore getMementoState(){
        return new Visitatore(nome,cognome);
    }
}
