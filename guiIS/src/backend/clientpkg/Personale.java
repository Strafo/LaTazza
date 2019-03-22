package backend.clientpkg;
import backend.Debito;
import backend.Euro;
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

    public void pagamentoDebito(Euro importo){
        debito.pagamentoDebito(this,importo);
    }

    public Euro getImportoDebito(){
        return debito.getImporto();
    }

    public Personale(String nome, String cognome,boolean attivo) {
        super(nome, cognome);
        this.attivo=attivo;
        debito= new Debito( new Euro(0));
    }

    public Personale(String nome, String cognome) {
        this(nome, cognome, true);
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
