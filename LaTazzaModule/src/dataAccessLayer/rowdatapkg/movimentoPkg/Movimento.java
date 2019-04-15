package dataAccessLayer.rowdatapkg.movimentoPkg;
import dataAccessLayer.rowdatapkg.clientPkg.Cliente;
import dataAccessLayer.rowdatapkg.AbstractEntryDB;
import dataAccessLayer.mementoPkg.Memento;
import java.sql.Timestamp;
import java.util.Objects;

public abstract class Movimento extends AbstractEntryDB {
    private Timestamp data;
    private Cliente cliente;

    public Movimento(Timestamp data, Cliente cliente) {
        this.data =Objects.requireNonNull(data);
        this.cliente = Objects.requireNonNull(cliente);
    }

    public Movimento(){}

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        setMementoIfNotDef();
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente){
        setMementoIfNotDef();
        this.cliente=cliente;}


    @Override
    public String toString() {
        return "Movimento: data:"+data.toString()+"  nome:"+cliente.getNome()+" cognome:"+cliente.getCognome();
    }

    @Override
    public abstract Memento createMemento();

    @Override
    public void undoChanges(){
        Movimento oldState=(Movimento) this.getMemento().getMementoState();
        this.data=oldState.getData();
        this.cliente=oldState.getCliente();
    }

}