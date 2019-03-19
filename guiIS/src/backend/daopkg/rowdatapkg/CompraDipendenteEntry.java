package backend.daopkg.rowdatapkg;

import java.util.Date;

public class CompraDipendenteEntry extends CompraVisitatoreEntry {
    private boolean contanti;

    public CompraDipendenteEntry(Date data, int numeroCialde, String tipoCialda, String nome, String cognome,boolean contanti) {
        super(data,numeroCialde,tipoCialda,nome,cognome);
        this.contanti=contanti;
    }

    public boolean isContanti() {
        return contanti;
    }

    public void setContanti(boolean contanti) {
        this.contanti = contanti;
    }


}
