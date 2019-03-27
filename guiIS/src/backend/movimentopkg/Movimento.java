package backend.movimentopkg;
import backend.clientpkg.Cliente;
import backend.daopkg.gateways.AbstractDao;
import backend.daopkg.rowdatapkg.AbstractEntryDB;
import backend.memento.Memento;
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

    public abstract Class<? extends AbstractDao> getCorrespondigDaoClass();

    @Override
    public String toString() {
        return "Movimento: data:"+data.toString()+"  nome:"+cliente.getNome()+" cognome:"+cliente.getCognome();
    }

    @Override
    public abstract Memento createMemento();


}