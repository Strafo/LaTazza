package backend.businessLogicLayer;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.dataAccessLayer.rowdatapkg.MagazzinoEntry;
import backend.dataAccessLayer.rowdatapkg.RifornimentoEntry;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Magazzino  {

    //Lista contente per ogni tipo di cialda il numero di cialde disponibili
    private Map<CialdeEntry,Integer> stato;
    //Il numero di cialde contenute in una scatola
    private static final int qtaCialdeScatole=50;

    public int getQtaCialdeScatole(){
        return qtaCialdeScatole;
    }
    //Inizializzazione del magazzino: quando il magazzino viene creato non sono presenti
    public Magazzino(){
        List<MagazzinoEntry>list=LaTazzaApplication.dao.getAll(MagazzinoEntry.class);//inizializza il campo list facendo query sul databaseConnectionHandler
        stato= new HashMap<>();
        for (MagazzinoEntry m: list ) {
            stato.put(m.getTipoCialda(),m.getNumeroCialde());
        }

    }



    //Copia lo stato del magazzino e lo ritorna
    public Map<CialdeEntry,Integer> getCopyStato(){
        return new HashMap<>(stato);

    }

    public boolean aggiungiScatole(CialdeEntry t, int qtaScatole) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Integer qta=qtaScatole*qtaCialdeScatole;
        RifornimentoEntry entry=new RifornimentoEntry(timestamp,qta,t.getTipo());
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

    //Se non sono presenti abbastanza cialde il metodo ritorna false
    public boolean rimuoviCialde(CialdeEntry t, int qta){
        Integer oldQta=stato.get(t);
        if(oldQta==null){return false;}
        int nuovaQta=stato.get(t) - qta;
        if(nuovaQta < 0) return false;
        stato.put(t, nuovaQta);

        return true;
    }




}
