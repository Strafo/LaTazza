package dataAccessLayer.mementoPkg;

import dataAccessLayer.rowdatapkg.clientPkg.Visitatore;

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
