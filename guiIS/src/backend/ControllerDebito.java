package backend;

import java.util.ArrayList;
import java.util.HashMap;

public class ControllerDebito {
    ControllerPersonale cp;
    public void ControllerDebito(ControllerPersonale cp){
        this.cp=cp;
    }

    public void registrarePagamentoDebito(Euro importo , Personale p){
        ArrayList<Personale> list=cp.getList();

        int index= list.indexOf(p);
        if(index == -1) return;
        Personale cliente=list.get(index);
        cliente.pagamentoDebito(importo);
    }



    public HashMap<Personale, Euro> esaminareDebitiPersonale(){
        HashMap<Personale,Euro> debiti= new HashMap<Personale,Euro>();
        ArrayList<Personale> list=cp.getList();
        for (Personale p:list) {
            debiti.put(p,p.getImportoDebito());
        }
        return debiti;
    }

}
