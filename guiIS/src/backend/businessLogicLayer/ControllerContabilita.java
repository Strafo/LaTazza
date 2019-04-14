package backend.businessLogicLayer;

import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Cliente;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Visitatore;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import utils.Euro;
import java.util.Map;
import java.util.Observable;

import static presentationLayer.guiLogicPkg.ObserverSubscriptionType.CONTABILITALIST;

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


    /**
     *
     * @param c
     * @param tipo
     * @param numeroCialde
     * @param contanti
     * @return
     * @throws Euro.OverflowEuroException
     * @throws IllegalArgumentException
     */
    public boolean registraVendita(Cliente c, CialdeEntry tipo, int numeroCialde, boolean contanti)
            throws Euro.OverflowEuroException,IllegalArgumentException
    {
        if( c instanceof Visitatore && !contanti){
            return false;
        }

        Euro importo= new Euro(tipo.getPrezzo());
        importo.moltiplicaImporto(numeroCialde);// throws Overflow, illegal
        if(!magazzino.rimuoviCialde(tipo,numeroCialde)){
            System.err.println("regVenditafalse");

            return false;
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
            return false;
        }
        this.setChanged();this.notifyObservers(CONTABILITALIST);
        return true;
    }


    /**
     *
     * @param tipo
     * @param numeroScatole
     * @return
     */
    public boolean registrareRifornimento(CialdeEntry tipo, int numeroScatole){
        Euro importo= new Euro(tipo.getPrezzo());
        int numeroCialde=numeroScatole*magazzino.getQtaCialdeScatole();
        importo.moltiplicaImporto(numeroCialde);
        if(!cassa.decrementaSaldo(importo)) return false;
        magazzino.aggiungiScatole(tipo,numeroScatole);
        this.setChanged();this.notifyObservers(CONTABILITALIST);
        return true;

    }

    public Euro statoCassa(){
        return  cassa.getCopySaldo();
    }

    public Map<CialdeEntry, Integer> statoMagazzino(){
        return  magazzino.getCopyStato();
    }


    /**GESTIONE CONSISTENZA**/

    private void handleMagazzinoConsistency(CialdeEntry tipo,int numeroCialde){
        try {
            if (!magazzino.aggiungiCialde(tipo, numeroCialde)) {
                //se fallisce anche il tentativo di gestione dell'errore abortisco.L'user dovrà quindi riavviare
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
            boolean resState;
            if (!contanti) {
                resState = controllerDebito.registrarePagamentoDebito(importo, c.getNome(), c.getCognome());
            } else{
                resState = cassa.decrementaSaldo(importo);
            }

            if(!resState){
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
