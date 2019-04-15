package dataAccessLayer.mementoPkg;

import dataAccessLayer.rowdatapkg.clientPkg.Cliente;
import dataAccessLayer.rowdatapkg.movimentoPkg.Movimento;
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
