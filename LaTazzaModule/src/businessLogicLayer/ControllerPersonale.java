package businessLogicLayer;

import dataAccessLayer.gatewaysPkg.IDaoFacade;
import dataAccessLayer.rowdatapkg.clientPkg.Personale;
import presentationLayer.LaTazzaApplication;
import utils.LaTazzaLogger;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import static businessLogicLayer.ObserverSubscriptionType.PERSONALELIST;

public class ControllerPersonale extends Observable {

    private List<Personale> listaPersonaleAttivo;
    private IDaoFacade dao;

    public ControllerPersonale(){
        dao=LaTazzaApplication.backEndInvoker.getDao();
        listaPersonaleAttivo=dao.getAll(Personale.class);
        List<Personale> listaPersonaleNonA=new LinkedList<>();
        if(listaPersonaleAttivo==null){//inizializzazione fallita... impossibile gestire l'errore->termino esecuzione
            LaTazzaLogger.log(new LogRecord(
                    Level.INFO,
                    "Impossibile inizializzare listaPersonaleAttivo (Controller personale).\n"));
        }else{
            for (Personale i:listaPersonaleAttivo) {//seleziono solo quelli attivi
                if(!i.isAttivo()) listaPersonaleNonA.add(i);
            }
            listaPersonaleAttivo.removeAll(listaPersonaleNonA);
        }
        this.setChanged();
    }

    /**
     * Cerca nella lista del personale attivo.
     * Ritorna il riferimento alla classe personale cercata.
     * @param nome
     * @param cognome
     * @return p se trovato, null altrimenti
     * @throws NullPointerException se nome o cognome sono null.
     */
    public Personale getPersonale(String nome,String cognome) throws NullPointerException{
        return getPersonale(new Personale(nome,cognome));
    }

    public Personale getPersonale(Personale personale)throws NullPointerException{
        Personale p=null;
        for(ListIterator<Personale>iter=listaPersonaleAttivo.listIterator();iter.hasNext();){
            p=iter.next();
            if(p.equals(personale))break;
        }
        return p;
    }


    /**
     * Ritorna una copia della lista del personale correntemente attivo.
     * @return la lista
     */
    public List<Personale> getCopyList(){
        return new ArrayList<>(listaPersonaleAttivo);
    }


    public boolean aggiungiPersonale(String nome, String cognome)throws NullPointerException{
        Personale p=new Personale(nome,cognome);//può lanciare null pointer exception!
        if(listaPersonaleAttivo.contains(p)) return false;
        if(!dao.save(p)){
            return false;
        }
        listaPersonaleAttivo.add(p);
        this.setChanged();this.notifyObservers(PERSONALELIST);
        return true;
    }

    public boolean licenziaPersonale(Personale p) {
        if (!listaPersonaleAttivo.contains(p)) return false;
        p.setAttivo(false);
        if (!dao.update(p)) {//se fallisce ripristino stato iniziale
            p.undoChanges();
            return false;
        } else {
            listaPersonaleAttivo.remove(p);
        }
        this.setChanged();this.notifyObservers(PERSONALELIST);
        return true;
    }

    public boolean licenziaPersonale(String nome,String cognome) {
        return licenziaPersonale(new Personale(nome,cognome));

    }
}
