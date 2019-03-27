package backend.memento;
import backend.clientpkg.Personale;

public class MementoPersonale extends MementoCliente implements Memento {

    private boolean attivo;

    @Override
    public <T> void setMementoState(T originator) {
        super.setMementoState(originator);
        this.attivo=((Personale)originator).isAttivo();
    }

    @Override
    public  Personale getMementoState(){
        return new Personale(super.nome,super.cognome,attivo);
    }
}
