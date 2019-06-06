package businessLogicLayer;

import businessLogicLayer.commandPkg.Command;
import dataAccessLayer.rowdatapkg.Cassa;
import dataAccessLayer.rowdatapkg.CialdeEntry;
import dataAccessLayer.rowdatapkg.clientPkg.Cliente;
import dataAccessLayer.rowdatapkg.clientPkg.Personale;
import dataAccessLayer.rowdatapkg.clientPkg.Visitatore;
import presentationLayer.LaTazzaApplication;
import utils.Euro;
import java.util.Map;
import java.util.Observable;
import static businessLogicLayer.ObserverSubscriptionType.CONTABILITALIST;
import static businessLogicLayer.commandPkg.Command.LaTazzaErrno.*;

public  class ControllerContabilita extends Observable {

    private Magazzino magazzino;
    private Cassa cassa;
    private ControllerDebito controllerDebito;

    public ControllerContabilita(){
        magazzino= new Magazzino();
        cassa= LaTazzaApplication.backEndInvoker.getDao().getAll(Cassa.class).get(0);
        setControllerDebito();
        this.setChanged();
    }

    public Command.LaTazzaErrno registraVendita(Cliente c, CialdeEntry tipo, int numeroCialde, boolean contanti)
            throws Euro.OverflowEuroException,IllegalArgumentException
    {
        if( c instanceof Visitatore && !contanti){
            return ERROREVISITATOREDEBITO;
        }

        Euro importo= new Euro(tipo.getPrezzo());
        importo.moltiplicaImporto(numeroCialde);// throws Overflow, illegal
        if(!magazzino.rimuoviCialde(tipo,numeroCialde)){
            return CIALDEINSUFF;
        }
        try {
            if (!contanti) {
                if(controllerDebito==null){
                    setControllerDebito();
                }
                controllerDebito.registrareAumentoDebito(importo, (Personale) c);//può lanciare OverflowEuroExc
            } else cassa.incrementaSaldo(importo);//può lanciare OverflowEuroExc
        }catch (Euro.OverflowEuroException e){
            //ripristino stato magazzino
            handleMagazzinoConsistency(tipo,numeroCialde);
            throw e;//rilancio l'eccezione per essere loggata
        }

        if(!Vendita.aggiungiMovimentoVendita(c,tipo,numeroCialde,contanti)){//se fallisce,riaggiungo le scatole
            handleMagazzinoConsistency(tipo,numeroCialde);
            handleDebitoCassaConsistency(importo,c,contanti);
            return ERROREDATABASE;
        }
        this.setChanged();this.notifyObservers(CONTABILITALIST);
        return NOERROR;
    }

    public Command.LaTazzaErrno registrareRifornimento(CialdeEntry tipo, int numeroScatole){
        Euro importo= new Euro(tipo.getPrezzo());
        int numeroCialde=numeroScatole*magazzino.getQtaCialdeScatole();
        importo.moltiplicaImporto(numeroCialde);
        if(!cassa.decrementaSaldo(importo)){
            return FONDIINSUFFICIENTI;
        }
        if(!magazzino.aggiungiScatole(tipo,numeroScatole)){
            return ERROREDATABASE;
        }
        this.setChanged();this.notifyObservers(CONTABILITALIST);
        return NOERROR;

    }

    public Euro statoCassa(){
        return  cassa.getCopySaldo();
    }

    public Map<CialdeEntry, Integer> statoMagazzino(){
        return  magazzino.getCopyStato();
    }

    /**
     * Raiggiorna la cassa chiedendo al DB.
     */
    public void updateCassa(){
        cassa= LaTazzaApplication.backEndInvoker.getDao().getAll(Cassa.class).get(0);
        this.setChanged();this.notifyObservers(CONTABILITALIST);
    }
    /**GESTIONE CONSISTENZA**/

    private void handleMagazzinoConsistency(CialdeEntry tipo,int numeroCialde){
        try {
            if (!magazzino.aggiungiCialde(tipo, numeroCialde)) {
                //se fallisce anche il tentativo di gestione dell'errore termino esec.L'utente dovrà quindi riavviare
                //l'applicazione restaurando così la consistenza DB, Ram
                //teoricamente non dovrebbe mai accadere in quanto rimuoviCialde() non è fallita... però non si sa mai...
                throw new Error(new Throwable("Inconsistenza nell'applicazione."));
            }
        }catch (Exception e){
            throw new Error("Inconsistenza nell'applicazione.",e);
        }
    }

    private void handleDebitoCassaConsistency(Euro importo,Cliente c,boolean contanti){
        try {
            boolean resState=true;
            Command.LaTazzaErrno lte=NOERROR;
            if (!contanti) {
                lte = controllerDebito.registrarePagamentoDebito(importo, c.getNome(), c.getCognome());
            } else{
                resState = cassa.decrementaSaldo(importo);
            }

            if(!resState||lte!=NOERROR){
                throw new Error(new Throwable("Inconsistenza nell'applicazione."));
            }
        }catch(Exception e){
            throw new Error("Inconsistenza nell'applicazione.",e);
        }
    }

    private void setControllerDebito(){
        this.controllerDebito=LaTazzaApplication.backEndInvoker.getControllerDebito();
    }



}
