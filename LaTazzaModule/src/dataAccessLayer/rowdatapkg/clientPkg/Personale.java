package dataAccessLayer.rowdatapkg.clientPkg;
import utils.Euro;
import dataAccessLayer.mementoPkg.Memento;
import dataAccessLayer.mementoPkg.MementoPersonale;

import java.util.Objects;


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

    public void aumentaDebito(Euro importo) throws Euro.OverflowEuroException,NullPointerException{
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

    public static class Debito  {
        private Euro quantita;

        public Debito(Euro quantita){

            this.quantita=Objects.requireNonNull(quantita);
        }

        public void sommaDebito(Euro importo) throws Euro.OverflowEuroException,NullPointerException {

            this.quantita.aggiungiImporto(importo);
        }

        public Euro getImporto(){
            return new Euro(quantita);
        }

        /**
         *
         * @param importo
         * @return se importo>quantita l'operazione viene abortita e ritorna false, altrimenti true
         */
        private boolean sottraiDebito(Euro importo) throws NullPointerException{
            if (Euro.compare(importo, quantita) > 0) return false;
            quantita.sottraiImporto(importo);
            return true;

        }



        public boolean pagamentoDebito(Personale pers,Euro importo) throws NullPointerException{
            return sottraiDebito(importo);
        }

        @Override
        public String toString() {
                return quantita.toString();
        }

    }
}
