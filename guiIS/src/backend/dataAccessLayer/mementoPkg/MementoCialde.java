package backend.dataAccessLayer.mementoPkg;
import backend.businessLogicLayer.Euro;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;

public class MementoCialde extends AbstractMemento implements Memento {

    private String tipo;
    private Euro prezzo;

    @Override
    public <T> void setMementoState(T originator) {
        this.tipo=((CialdeEntry)originator).getTipo();
        this.prezzo=((CialdeEntry)originator).getPrezzo();
    }

    @Override
    public CialdeEntry getMementoState() {
        return new CialdeEntry(tipo,prezzo);
    }
}
