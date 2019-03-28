package backend.dataAccessLayer.mementoPkg;

import backend.dataAccessLayer.rowdatapkg.clientPkg.Cliente;

public abstract class MementoCliente extends AbstractMemento implements Memento{
    protected String nome;
    protected String cognome;

    @Override
    public abstract Cliente getMementoState();

    @Override
    public <T> void setMementoState(T originator) {
        this.nome=((Cliente)originator).getNome();
        this.cognome=((Cliente)originator).getCognome();
    }
}
