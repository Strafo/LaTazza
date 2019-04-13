package backend.businessLogicLayer;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.dataAccessLayer.rowdatapkg.MagazzinoEntry;
import backend.dataAccessLayer.rowdatapkg.RifornimentoEntry;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Magazzino  {

    //Lista contente per ogni tipo di cialda il numero di cialde disponibili
    private Map<CialdeEntry,Integer> stato;
    //Il numero di cialde contenute in una scatola
    private static final int qtaCialdeScatole=50;

    int getQtaCialdeScatole(){
        return qtaCialdeScatole;
    }

    /**
     *
     */
    Magazzino(){
        List<MagazzinoEntry>list=LaTazzaApplication.dao.getAll(MagazzinoEntry.class);//inizializza il campo list facendo query sul databaseConnectionHandler
        if(list==null){
            throw new Error(new Throwable("Impossibile creare Magazzino nell'applicazione."));
        }
        stato= new HashMap<>();//todo settare load factor
        for (MagazzinoEntry m: list ) {
            stato.put(m.getTipoCialda(),m.getNumeroCialde());
        }
    }


    /**
     * Copia lo stato del magazzino e lo ritorna
     * @return la mappa contente associazione tipocialda-Quantità
     */
    Map<CialdeEntry,Integer> getCopyStato(){
        return new HashMap<>(stato);

    }

    /**
     *
     * @param t
     * @param qtaScatole
     * @return
     * @throws NullPointerException
     */
    boolean aggiungiScatole(CialdeEntry t, int qtaScatole)throws NullPointerException{
        Integer qta=qtaScatole*qtaCialdeScatole;
        RifornimentoEntry entry=new RifornimentoEntry(new Timestamp((new Date()).getTime()),qta,t.getTipo());//nullp
        Integer oldQta;
        if(LaTazzaApplication.dao.save(entry)){
            if((oldQta=stato.getOrDefault(t,null))!=null){//se contiene già un entry per il tipo faccio somma del numero cialde
                stato.put(t,qta+oldQta);
            }else{
                stato.put(t,qta);
            }
            return true;
        }else{
           return false;
        }
    }

    /**
     * Rimuove la quantità passata di cialde dal magazzino.
     * @param t il tipo di cialda
     * @param qta
     * @return true se il tipo di cialda passata esiste e se qta<=riserveMagazzino  ,false altrimenti
     */
    boolean rimuoviCialde(CialdeEntry t, int qta){
        Integer oldQta=stato.get(t);
        if(oldQta==null){return false;}
        int nuovaQta=oldQta - qta;
        if(nuovaQta < 0) return false;
        stato.put(t, nuovaQta);
        return true;
    }

    /**
     * Aggiunge la quantità passata di cialde al magazzino.
     * @param t il tipo di cialda
     * @param qta
     * @return true se il tipo di cialda passata esiste ,false altrimenti
     */
    boolean aggiungiCialde(CialdeEntry t,int qta){
        return rimuoviCialde(t,-qta);
    }




}
