package backend.clientpkg;
import backend.Debito;
import backend.daopkg.gateways.PersonaleDao;


public final class Personale extends Cliente  {


    private Debito debito;
    private boolean attivo;

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {//todo denvo essere consiste con il database per�
        this.attivo = attivo;
    }

    public Debito getDebito() {
        return debito;
    }

    public void setDebito(Debito debito) {
        this.debito = debito;
    }


    public Personale(String nome, String cognome,boolean attivo) {
        super(nome, cognome);
        this.attivo=attivo;
    }

    public Personale(String nome, String cognome) {
        super(nome, cognome);
        this.attivo=true;
    }

    public Personale(){}

    @Override
    public String toString() {
        if (debito != null) {
            return super.toString() + " debito:" + debito.toString() + " attivo:" + attivo + "(Personale)";
        }else{
            return super.toString() + " debito:null attivo:" + attivo + "(Personale)";
        }
    }

    @Override
    public Class<PersonaleDao> getCorrespondigDaoClass() {
        return PersonaleDao.class;
    }


}
