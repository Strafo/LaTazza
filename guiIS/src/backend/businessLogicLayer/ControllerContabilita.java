package backend.businessLogicLayer;

import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Cliente;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Visitatore;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import utils.Euro;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public  class ControllerContabilita {

    private Magazzino magazzino;
    private Cassa cassa;


    public ControllerContabilita(){
        magazzino= new Magazzino();
        cassa= LaTazzaApplication.dao.getAll(Cassa.class).get(0);
    }


    public boolean registraVendita(Cliente c, CialdeEntry tipo, int numeroCialde, boolean contanti){
        if( c instanceof Visitatore && !contanti) return false;
        Date date=new Date();
        Euro importo= new Euro(tipo.getPrezzo());
        importo.moltiplicaImporto(numeroCialde);
        LaTazzaApplication.dao.startTransaction();
            magazzino.rimuoviCialde(tipo,numeroCialde);
            Vendita.aggiungiVendita(new Timestamp(date.getTime()),c,tipo,numeroCialde,contanti);
        LaTazzaApplication.dao.endTransaction();
        if(!LaTazzaApplication.dao.getTransactionStatus()) return false;
        if(!contanti){

            LaTazzaApplication.controllerDebito.registrareAumentoDebito(importo,(Personale) c);
        }
        else cassa.incrementaSaldo(importo);
        return true;
    }


    public void registrareRifornimento(CialdeEntry tipo, int numeroScatole){
        magazzino.aggiungiScatole(tipo,numeroScatole);
        Euro importo= new Euro(tipo.getPrezzo());
        int numeroCialde=numeroScatole*magazzino.getQtaCialdeScatole();
        importo.moltiplicaImporto(numeroCialde);
        cassa.decrementaSaldo(importo);
    }

    public Euro statoCassa(){
        return  cassa.getCopySaldo();
    }

    public Map<CialdeEntry, Integer> statoMagazzino(){
        return  magazzino.getCopyStato();
    }
}
