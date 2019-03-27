package backend.businessLogicLayer;

import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Visitatore;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.dataAccessLayer.rowdatapkg.movimentoPkg.MovimentoVendita;

import java.sql.Timestamp;

public class Vendita {


    public boolean aggiungiVenditaVisitatore(Timestamp data, Visitatore visitatore, CialdeEntry tipoCialda, int qta){

    MovimentoVendita mv= new MovimentoVendita(data,visitatore,qta,tipoCialda,true);//la vendita è per forza con contanti
    return mv.aggiungiVendita();

    }

    /**
     * @param modalita true se con contanti, false se vendita con debito
     */
    public boolean aggiungiVenditaPersonale(Timestamp data, Personale personale, CialdeEntry tipoCialda, int qta, boolean modalita){

        MovimentoVendita mp= new MovimentoVendita(data,personale,qta,tipoCialda,modalita);
        return mp.aggiungiVendita();

    }
}
