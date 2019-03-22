package backend;
import backend.clientpkg.Personale;
import backend.movimentopkg.MovimentoDebito;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ControllerDebito {
    ControllerPersonale controller;
    public void ControllerDebito(ControllerPersonale cp){
        this.controller=cp;
    }

    private void aggiornaMovimento(Personale p, Euro importo){
        MovimentoDebito movimentoDebito=new MovimentoDebito(new Date(),p,importo);
        movimentoDebito.aggiornaDebito();
    }

    public void registrarePagamentoDebito(Euro importo , Personale p){
        List<Personale> list=controller.getCopyList();
        int index= list.indexOf(p);
        if(index == -1) return;
        Personale cliente=list.get(index);
        cliente.pagamentoDebito(importo);
        aggiornaMovimento(cliente, importo);
    }




    public HashMap<Personale, Euro> esaminareDebitiPersonale(){
        HashMap<Personale,Euro> debiti= new HashMap<Personale,Euro>();
        ArrayList<Personale> list= (ArrayList<Personale>)controller.getCopyList();
        for (Personale p:list) {
            debiti.put(p,p.getImportoDebito());
        }
        return debiti;
    }

}
