package backend.clientpkg;

import backend.daopkg.gateways.VisitatoreDao;
import backend.memento.Memento;
import backend.memento.MementoVisitatore;

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


}
