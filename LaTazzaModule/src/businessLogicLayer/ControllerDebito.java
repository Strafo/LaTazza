package businessLogicLayer;
import dataAccessLayer.gatewaysPkg.IDaoFacade;
import dataAccessLayer.rowdatapkg.clientPkg.Personale;
import dataAccessLayer.rowdatapkg.movimentoPkg.Movimento;
import dataAccessLayer.rowdatapkg.movimentoPkg.MovimentoDebito;
import presentationLayer.LaTazzaApplication;
import utils.Euro;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import static businessLogicLayer.ObserverSubscriptionType.DEBITOLIST;


public  final class ControllerDebito extends Observable {

    private IDaoFacade dao;
    private ControllerPersonale controllerPersonale;
    public   ControllerDebito(){
        dao=LaTazzaApplication.backEndInvoker.getDao();
        controllerPersonale=LaTazzaApplication.backEndInvoker.getControllerPersonale();
        this.setChanged();
    }


    /**
     *
     * @param p
     * @param importo
     * @return
     * @throws NullPointerException
     */
    private  boolean aggiungiMovimentoDebito(Personale p, Euro importo) throws NullPointerException{
        Date date=new Date();
        Movimento mp=new MovimentoDebito(new Timestamp(date.getTime()),p,importo);
        this.setChanged();this.notifyObservers(DEBITOLIST);
        return dao.save(mp);
    }


    /**
     *
     * @param importo
     * @param nome
     * @param cognome
     * @return
     * @throws NullPointerException
     */
    public  boolean registrarePagamentoDebito(Euro importo , String nome,String cognome )throws NullPointerException{
        Personale cliente= controllerPersonale.getPersonale(nome, cognome);
        //todo deve essere un transazione
            aggiungiMovimentoDebito(cliente, importo);
            if(!cliente.pagamentoDebito(importo)) return false;
        //todo fino a qui
        this.setChanged();this.notifyObservers(DEBITOLIST);
        return true;
    }


    /**
     *
     * @param importo
     * @param nome
     * @param cognome
     * @return
     * @throws NullPointerException
     */
    public  boolean registrareAumentoDebito(Euro importo ,String nome,String cognome)throws NullPointerException{
        Personale cliente = controllerPersonale.getPersonale(nome, cognome);
        if (cliente == null) return false;
        cliente.aumentaDebito(importo);
        this.setChanged();this.notifyObservers(DEBITOLIST);
        return true;
    }

    public  boolean registrareAumentoDebito(Euro importo ,Personale personale)throws Euro.OverflowEuroException,NullPointerException{
        Personale cliente = controllerPersonale.getPersonale(personale);
        if (cliente == null) return false;
        cliente.aumentaDebito(importo);
        this.setChanged();this.notifyObservers(DEBITOLIST);
        return true;
    }


    /**
     * Ritorna la lista del personale attivo con un debito >0.
     * @return la lista
     */
    public  List<Personale> esaminareDebitiPersonale(){
        List<Personale> list= controllerPersonale.getCopyList();
        list.removeIf(p -> Euro.compare(p.getImportoDebito(), new Euro(0, 0)) == 0);
        return list;
    }


}
