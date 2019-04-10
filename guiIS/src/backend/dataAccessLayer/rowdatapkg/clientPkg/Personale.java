package backend.dataAccessLayer.rowdatapkg.clientPkg;
import backend.businessLogicLayer.Debito;
import utils.Euro;
import backend.dataAccessLayer.mementoPkg.Memento;
import backend.dataAccessLayer.mementoPkg.MementoPersonale;


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
        this.debito = debito;
    }

    public void aumentaDebito(Euro importo) throws NullPointerException{
        debito.sommaDebito(importo);
    }

    public boolean pagamentoDebito(Euro importo) throws NullPointerException{

        return debito.pagamentoDebito(this,importo);
    }

    public Euro getImportoDebito(){
        return debito.getImporto();
    }

    public Personale(String nome, String cognome,boolean attivo, Euro debito) {
        super(nome, cognome);
        this.attivo=attivo;
        this.debito= new Debito( debito);
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
    public void undoChanges(){
        super.undoChanges();
        Personale oldState=(Personale) this.getMemento().getMementoState();
        this.attivo=oldState.isAttivo();
        //debito non viene toccato perchè non fa parte di memento
        removeMemento();
    }
}
