package backend.businessLogicLayer;

import java.util.EnumMap;

public class Magazzino {

    //Lista contente per ogni tipo di cialda il numero di cialde disponibili
    private EnumMap<TipoCialda,Integer> stato;
    //Il numero di cialde contenute in una scatola
    private static final int qtaCialdeScatole=50;

    public int getQtaCialdeScatole(){
        return qtaCialdeScatole;
    }
    //Inizializzazione del magazzino: quando il magazzino viene creato non sono presenti
    public Magazzino(){
        stato= new EnumMap(TipoCialda.class);
        for(TipoCialda t: TipoCialda.values() ){
            stato.put(t,0);
        }
    }
    //Copia lo stato del magazzino e lo ritorna
    public EnumMap<TipoCialda,Integer> getCopyStato(){
        EnumMap<TipoCialda,Integer> copy= new EnumMap(stato);
        return copy;
    }

    public void aggiungiScatole(TipoCialda t, int qta) {
        stato.put(t, stato.get(t) + qta*qtaCialdeScatole);
    }

    //Se non sono presenti abbastanza cialde il metodo ritorna false
    public boolean rimuoviCialde(TipoCialda t, int qta){
        int nuovaQta=stato.get(t) - qta;
        if(nuovaQta < 0) return false;
        stato.put(t, nuovaQta);
        return true;
    }



}
