package backend.clientpkg;

import backend.daopkg.gateways.VisitatoreDao;
import backend.daopkg.rowdatapkg.Memento;

public final class Visitatore extends Cliente {

    public Visitatore(String nome, String cognome) {
        super(nome, cognome);
    }

    public Visitatore(){}

    @Override
    public Class<VisitatoreDao> getCorrespondigDaoClass() {
        return VisitatoreDao.class;
    }

    @Override
    public String toString(){
        return super.toString()+"(Visitatore)";
    }

    @Override
    public Memento createMemento() {
        return new MementoVisitatore();
    }

    private class MementoVisitatore extends MementoCliente implements Memento {

        @Override
        public <T> void setMementoState(T originator) {
            super.setMementoState(originator);
        }

        @Override
        public  Visitatore getMementoState(){
            return new Visitatore(nome,cognome);
        }
    }

}
