package backend.daopkg.rowdatapkg;
import backend.Euro;
import backend.daopkg.gateways.PagamentoDebitoDao;

import java.util.Date;

public class PagamentoDebitoEntry extends AbstractEntryDB implements EntryDB {

    private String nomePersonale;
    private String cognomePersonale;
    private Date data;
    private Euro importo;

    public PagamentoDebitoEntry(String nomePersonale, String cognomePersonale, Date data, Euro importo) {
        this.nomePersonale = nomePersonale;
        this.cognomePersonale = cognomePersonale;
        this.data = data;
        this.importo = importo;
    }

    public PagamentoDebitoEntry(){}

    public String getNomePersonale() {
        return nomePersonale;
    }

    public void setNomePersonale(String nomePersonale) {
        this.nomePersonale = nomePersonale;
    }

    public String getCognomePersonale() {
        return cognomePersonale;
    }

    public void setCognomePersonale(String cognomePersonale) {
        this.cognomePersonale = cognomePersonale;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Euro getImporto() {
        return importo;
    }

    public void setImporto(Euro importo) {
        this.importo = importo;
    }

    @Override
    public String toString() {
        return "PagamentoDebitoEntry: data:"+data.toString()+" importo:"+importo.toString()+" nome:"+nomePersonale+" cognome:"+cognomePersonale;
    }

    @Override
    public Class<PagamentoDebitoDao> getCorrespondigDao() {
        return PagamentoDebitoDao.class;
    }
}
