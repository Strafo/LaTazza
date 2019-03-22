package backend.clientpkg;

import backend.daopkg.gateways.VisitatoreDao;

public final class Visitatore extends Cliente {
    public Visitatore(String nome, String cognome) {
        super(nome, cognome);
    }

    public Visitatore(){};

    @Override
    public Class<VisitatoreDao> getCorrespondigDaoClass() {
        return VisitatoreDao.class;
    }

    @Override
    public String toString(){
        return super.toString()+"(Visitatore)";
    }

}
