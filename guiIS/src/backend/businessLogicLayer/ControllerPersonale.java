package backend.businessLogicLayer;

import backend.dataAccessLayer.gatewaysPkg.IDaoFacade;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;

public class ControllerPersonale extends Observable {

    private List<Personale> listaPersonaleAttivo;
    private IDaoFacade dao;

    public ControllerPersonale(){
        dao=LaTazzaApplication.backEndInvoker.getDao();
        listaPersonaleAttivo=dao.getAll(Personale.class);
        List<Personale> listaPersonaleNonA=new LinkedList<>();
        if(listaPersonaleAttivo==null){//inizializzazione fallita...
            //todo cosa fare?
        }else{
            for (Personale i:listaPersonaleAttivo) {//seleziono solo quelli attivi
                if(!i.isAttivo()){
                    listaPersonaleNonA.add(i);
                }
            }
            listaPersonaleAttivo.removeAll(listaPersonaleNonA);
        }
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


    /**
     *
     * @param personale
     * @return
     * @throws NullPointerException
     */
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
        return  new LinkedList<>(listaPersonaleAttivo);
    }

    /**
     *
     * @param nome
     * @param cognome
     * @return
     * @throws NullPointerException
     */
    public boolean aggiungiPersonale(String nome, String cognome)throws NullPointerException{
        Personale p=new Personale(nome,cognome);//può lanciare null pointer exception!
        if(listaPersonaleAttivo.contains(p)) return false;
        if(!dao.save(p)){
            return false;
        }
        listaPersonaleAttivo.add(p);
        return true;
    }

    /**
     *
     * @param p
     * @return
     */
    public boolean licenziaPersonale(Personale p) {
        if (!listaPersonaleAttivo.contains(p)) return false;
        p.setAttivo(false);
        if (!dao.update(p)) {//se fallisce ripristino stato iniziale
            p.undoChanges();
            return false;
        } else {
            listaPersonaleAttivo.remove(p);
        }
        return true;
    }

    /**
     *
     * @param nome
     * @param cognome
     * @return
     */
    public boolean licenziaPersonale(String nome,String cognome) {
        return licenziaPersonale(new Personale(nome,cognome));

    }
}
