package backend.clientpkg;
import backend.Debito;
import backend.Euro;
import backend.daopkg.gateways.PersonaleDao;
import backend.daopkg.rowdatapkg.Memento;


public final class Personale extends Cliente  {


    private Debito debito;
    private boolean attivo;

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        setMementoIfNotDef();
        this.attivo = attivo;
    }

    public Debito getDebito() {
        return debito;
    }

    public void setDebito(Debito debito) {
        setMementoIfNotDef();
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
    public Memento createMemento() {
        return new MementoPersonale();
    }

    @Override
    public Class<PersonaleDao> getCorrespondigDaoClass() {
        return PersonaleDao.class;
    }

    private class MementoPersonale extends MementoCliente implements Memento {

        private boolean attivo;

        @Override
        public <T> void setMementoState(T originator) {
            super.setMementoState(originator);
            this.attivo=((Personale)originator).attivo;
        }

        @Override
        public  Personale getMementoState(){
            return new Personale(nome,cognome,attivo);
        }
    }

}
