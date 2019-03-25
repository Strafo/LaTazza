package backend.clientpkg;
import backend.daopkg.gateways.AbstractDao;
import backend.daopkg.rowdatapkg.AbstractEntryDB;

import java.util.Objects;

public abstract class Cliente extends AbstractEntryDB  {

    private String nome;
    private String cognome;


    public Cliente(String nome,String cognome){
        setNome(nome);
        setCognome(cognome);
    }

    public Cliente(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {

        this.nome =Objects.requireNonNull(nome);
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {

        this.cognome =Objects.requireNonNull(cognome);
    }

    public abstract Class<? extends AbstractDao>getCorrespondigDaoClass();


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
}
