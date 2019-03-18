package backend;

import java.util.Objects;

public abstract class Cliente {

    private String nome;
    private String cognome;

    public Cliente(String nome,String cognome){
        setNome(nome);
        setCognome(cognome);
    }

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
}
