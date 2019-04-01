package backend.businessLogicLayer;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import backend.dataAccessLayer.rowdatapkg.movimentoPkg.Movimento;
import backend.dataAccessLayer.rowdatapkg.movimentoPkg.MovimentoDebito;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import utils.Euro;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public  final class ControllerDebito {

    private  ControllerDebito(){}


    private static boolean aggiornaMovimento(Personale p, Euro importo){
        Date date=new Date();
        Movimento mp=new MovimentoDebito(new Timestamp(date.getTime()),p,importo);
        return LaTazzaApplication.dao.save(mp);
    }

    public static boolean registrarePagamentoDebito(Euro importo , Personale p){
        List<Personale> list=LaTazzaApplication.controllerPersonale.getCopyList();
        int index= list.indexOf(p);
        if(index == -1) return false;
        Personale cliente=list.get(index);
        cliente.pagamentoDebito(importo);
        aggiornaMovimento(cliente, importo);
        return true;
    }




    public static HashMap<Personale, Euro> esaminareDebitiPersonale(){
        HashMap<Personale, Euro> debiti= new HashMap<Personale,Euro>();
        List<Personale> list= LaTazzaApplication.controllerPersonale.getCopyList();
        for (Personale p:list) {
            debiti.put(p,p.getImportoDebito());
        }
        return debiti;
    }


}
