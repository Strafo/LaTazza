package backend.dataAccessLayer.rowdatapkg;

import backend.businessLogicLayer.ControllerCialde;
import backend.businessLogicLayer.Magazzino;
import backend.dataAccessLayer.mementoPkg.Memento;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Cliente;

public class MagazzinoEntry extends AbstractEntryDB {

    private CialdeEntry tipoCialda;
    private Integer numeroCialde;


    public MagazzinoEntry(String cialda, int qta){
        tipoCialda=ControllerCialde.getCialda(cialda);
        numeroCialde=qta;

    }
    public CialdeEntry getTipoCialda() {
        return tipoCialda;
    }

    public Integer getNumeroCialde(){
        return numeroCialde;
    }
    @Override
    public String toString() {
        return "MagazzinoEntry: tipo cialda:"+ tipoCialda.toString()+" quantità:"+numeroCialde;
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof MagazzinoEntry)) return false;
        if(this == obj) return true;
        MagazzinoEntry c= (MagazzinoEntry) obj;
        return tipoCialda.equals(c.getTipoCialda()) ;
    }

    @Override
    public Memento createMemento() {
        throw  new UnsupportedOperationException();
    }

    @Override
    public void undoChanges() {
        throw  new UnsupportedOperationException();
    }
}
