package backend.memento;

import backend.clientpkg.Cliente;
import backend.movimentopkg.Movimento;
import java.sql.Timestamp;

public abstract class MementoMovimento extends AbstractMemento implements Memento {

    protected Timestamp data;
    protected Cliente cliente;

    @Override
    public <T> void setMementoState(T originator) {
        this.data=((Movimento)originator).getData();
        this.cliente=((Movimento)originator).getCliente();
    }

    @Override
    public abstract Movimento getMementoState();
}
