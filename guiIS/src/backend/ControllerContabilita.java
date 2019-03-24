package backend;

import backend.clientpkg.Cliente;
import backend.clientpkg.Personale;
import backend.clientpkg.Visitatore;
import backend.daopkg.rowdatapkg.CialdeEntry;
import backend.movimentopkg.MovimentoDebito;

import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;

public class ControllerContabilita {

    Magazzino magazzino;
    Cassa cassa;

    public void ControllerPersonale(){

        magazzino= new Magazzino();
        cassa= new Cassa();
    }

    private static boolean venditaVisitatore(Visitatore v, TipoCialda tipo, int cialde){

        Vendita vendita = new Vendita();
        CialdeEntry tipoCialde= new CialdeEntry(tipo.toString());
        return  vendita.aggiungiVenditaVisitatore(new Date(), v, tipoCialde, cialde);
    }

    private static boolean venditaPersonale(Personale p, TipoCialda tipo, int cialde,boolean contanti){
        Vendita vendita = new Vendita();
        CialdeEntry tipoCialde= new CialdeEntry(tipo.toString());
        return  vendita.aggiungiVenditaPersonale(new Date(), p, tipoCialde, cialde,contanti);
    }


    public boolean registrareVendita(Cliente c, TipoCialda tipo, int numeroCialde, boolean contanti){
        boolean eseguita;
        if( c instanceof Visitatore) {
            if (!contanti) return false;
            eseguita = venditaVisitatore((Visitatore) c, tipo, numeroCialde);
        }
        else eseguita= venditaPersonale((Personale) c,tipo, numeroCialde, contanti);

        if(eseguita) eseguita=magazzino.rimuoviCialde(tipo, numeroCialde);
        return eseguita;
    }


    public void registrareRifornimento(TipoCialda tipo, int numeroScatole){
        magazzino.aggiungiScatole(tipo,numeroScatole);
    }

    public Euro statoCassa(){
        return  cassa.getCopySaldo();
    }

    public EnumMap<TipoCialda, Integer> statoMagazzino(){
        return  magazzino.getCopyStato();
    }
}
