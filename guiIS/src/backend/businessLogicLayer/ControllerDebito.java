package backend.businessLogicLayer;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import backend.dataAccessLayer.rowdatapkg.movimentoPkg.Movimento;
import backend.dataAccessLayer.rowdatapkg.movimentoPkg.MovimentoDebito;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import utils.Euro;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public  final class ControllerDebito {

    private  ControllerDebito(){
    }


    /**
     *
     * @param p
     * @param importo
     * @return
     * @throws NullPointerException
     */
    private static boolean aggiungiMovimentoDebito(Personale p, Euro importo) throws NullPointerException{
        Date date=new Date();
        Movimento mp=new MovimentoDebito(new Timestamp(date.getTime()),p,importo);
        return LaTazzaApplication.dao.save(mp);
    }


    /**
     *
     * @param importo
     * @param nome
     * @param cognome
     * @return
     * @throws NullPointerException
     */
    public static boolean registrarePagamentoDebito(Euro importo , String nome,String cognome )throws NullPointerException{
        Personale cliente= LaTazzaApplication.controllerPersonale.getPersonale(nome, cognome);
        //todo deve essere un transazione
            aggiungiMovimentoDebito(cliente, importo);
            if(!cliente.pagamentoDebito(importo)) return false;
        //todo fino a qui
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
    public static boolean registrareAumentoDebito(Euro importo ,String nome,String cognome)throws NullPointerException{
        Personale cliente = LaTazzaApplication.controllerPersonale.getPersonale(nome, cognome);
        if (cliente == null) return false;
        cliente.aumentaDebito(importo);
        return true;
    }

    public static boolean registrareAumentoDebito(Euro importo ,Personale personale)throws Euro.OverflowEuroException,NullPointerException{
        Personale cliente = LaTazzaApplication.controllerPersonale.getPersonale(personale);
        if (cliente == null) return false;
        cliente.aumentaDebito(importo);
        return true;
    }



    /**
     * Ritorna la lista del personale attivo con un debito >0.
     * @return la lista
     */
    public static List<Personale> esaminareDebitiPersonale(){
        List<Personale> list= LaTazzaApplication.controllerPersonale.getCopyList();
        list.removeIf(p -> Euro.compare(p.getImportoDebito(), new Euro(0, 0)) == 0);
        return list;
    }


}
