package backend.daopkg.rowdatapkg;
import backend.daopkg.gateways.CompraDipendenteDao;
import java.util.Date;

public class CompraDipendenteEntry extends CompraVisitatoreEntry  {
    private boolean contanti;

    public CompraDipendenteEntry(Date data, int numeroCialde, String tipoCialda, String nome, String cognome,boolean contanti) {
        super(data,numeroCialde,tipoCialda,nome,cognome);
        this.contanti=contanti;
    }

    public CompraDipendenteEntry(){}

    public boolean isContanti() {
        return contanti;
    }

    public void setContanti(boolean contanti) {
        this.contanti = contanti;
    }

    @Override
    public Class<CompraDipendenteDao> getCorrespondigDaoClass() {
        return CompraDipendenteDao.class;
    }
}
