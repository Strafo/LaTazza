package dataAccessLayer.rowdatapkg.clientPkg;
import dataAccessLayer.rowdatapkg.AbstractEntryDB;
import dataAccessLayer.mementoPkg.Memento;
import java.util.Objects;

public abstract class Cliente extends AbstractEntryDB  {

    private String nome;
    private String cognome;

    public Cliente(String nome,String cognome){
        this.nome =Objects.requireNonNull(nome);
        this.cognome =Objects.requireNonNull(cognome);
    }

    public Cliente(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        setMementoIfNotDef();
        this.nome =Objects.requireNonNull(nome);
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        setMementoIfNotDef();
        this.cognome =Objects.requireNonNull(cognome);
    }


    @Override
    public String toString() {
        return "cliente: nome:"+nome+" cognome:"+cognome;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Cliente)) return false;
        if(this == obj) return true;
        Cliente c= (Cliente) obj;
        return nome.equals(c.getNome()) && cognome.equals(c.getCognome());
    }

    @Override
    public abstract Memento createMemento();

    @Override
    public void undoChanges(){
        Cliente oldState=(Cliente) this.getMemento().getMementoState();
        this.nome=oldState.getNome();
        this.cognome=oldState.getCognome();
    }

}
