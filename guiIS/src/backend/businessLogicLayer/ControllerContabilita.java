package backend.businessLogicLayer;

import backend.dataAccessLayer.rowdatapkg.clientPkg.Cliente;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Visitatore;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;

import java.sql.Timestamp;
import java.util.Date;
import java.util.EnumMap;

public class ControllerContabilita {

    Magazzino magazzino;
    Cassa cassa;

    public void ControllerPersonale(){

        magazzino= new Magazzino();
        cassa= new Cassa();
    }

    private static boolean venditaVisitatore(Visitatore v, TipoCialda tipo, int cialde){
        Date date=new Date();
        Vendita vendita = new Vendita();
        CialdeEntry tipoCialde= new CialdeEntry(tipo.toString());
        return  vendita.aggiungiVenditaVisitatore(new Timestamp(date.getTime()), v, tipoCialde, cialde);
    }

    private static boolean venditaPersonale(Personale p, TipoCialda tipo, int cialde,boolean contanti){
        Vendita vendita = new Vendita();
        Date date=new Date();
        CialdeEntry tipoCialde= new CialdeEntry(tipo.toString());
        return  vendita.aggiungiVenditaPersonale(new Timestamp(date.getTime()), p, tipoCialde, cialde,contanti);
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
