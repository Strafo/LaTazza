package backend.dataAccessLayer.rowdatapkg;
import utils.Euro;
import backend.dataAccessLayer.gatewaysPkg.CialdeDao;
import backend.dataAccessLayer.mementoPkg.Memento;
import backend.dataAccessLayer.mementoPkg.MementoCialde;

public class CialdeEntry extends AbstractEntryDB   {
    private String tipo;
    private Euro prezzo;

    public CialdeEntry(String tipo, Euro prezzo) {
        this.tipo = tipo;
        this.prezzo = prezzo;
    }

    public CialdeEntry(String tipo){
        this.tipo=tipo;
    }

    public CialdeEntry(){}


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        setMementoIfNotDef();
        this.tipo = tipo;
    }

    public Euro getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Euro prezzo) {
        setMementoIfNotDef();
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        return "CialdeEntry: prezzo:" + prezzo + " tipo:" + tipo;
    }


    @Override
    public Class<CialdeDao> getCorrespondigDaoClass() {
        return CialdeDao.class;
    }

    @Override
    public Memento createMemento() {
        return new MementoCialde();
    }

    @Override
    public void undoChanges(){
        CialdeEntry oldState=(CialdeEntry) this.getMemento().getMementoState();
        this.tipo=oldState.getTipo();
        this.prezzo=oldState.getPrezzo();
        removeMemento();
    }


}
