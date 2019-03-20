package backend.daopkg.rowdatapkg;
import backend.daopkg.gateways.AbstractDao;
import backend.daopkg.gateways.CompraVisitatoreDao;
import java.util.Date;

public class CompraVisitatoreEntry extends AbstractEntryDB  {
    private Date data;
    private int numeroCialde;
    private String tipoCialda;
    private String nome;
    private String cognome;

    public CompraVisitatoreEntry(Date data, int numeroCialde, String tipoCialda, String nome, String cognome) {
        this.data = data;
        this.numeroCialde = numeroCialde;
        this.tipoCialda = tipoCialda;
        this.nome = nome;
        this.cognome = cognome;
    }

    public CompraVisitatoreEntry(){}

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getNumeroCialde() {
        return numeroCialde;
    }

    public void setNumeroCialde(int numeroCialde) {
        this.numeroCialde = numeroCialde;
    }

    public String getTipoCialda() {
        return tipoCialda;
    }

    public void setTipoCialda(String tipoCialda) {
        this.tipoCialda = tipoCialda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }


    @Override
    public Class<? extends AbstractDao> getCorrespondigDaoClass() {
        return CompraVisitatoreDao.class;
    }
}
