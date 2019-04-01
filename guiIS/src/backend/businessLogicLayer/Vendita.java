package backend.businessLogicLayer;

import backend.dataAccessLayer.rowdatapkg.clientPkg.Cliente;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Visitatore;
import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.dataAccessLayer.rowdatapkg.movimentoPkg.Movimento;
import backend.dataAccessLayer.rowdatapkg.movimentoPkg.MovimentoVendita;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import java.sql.Timestamp;

public final class Vendita {

    private Vendita(){}

    public static boolean aggiungiVendita(Timestamp data, Cliente cliente, CialdeEntry tipoCialda, int qta, boolean modalita){
        Movimento mp;

        if(cliente instanceof Personale){
            mp=new MovimentoVendita(data,cliente,qta,tipoCialda,modalita);
        }else{
            if(cliente instanceof Visitatore){
                mp=new MovimentoVendita(data,cliente,qta,tipoCialda,true);
            }else{
                return false;
            }
        }
        return LaTazzaApplication.dao.save(mp);
    }
}
