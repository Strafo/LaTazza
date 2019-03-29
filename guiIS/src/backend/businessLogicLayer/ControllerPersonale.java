package backend.businessLogicLayer;

import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import java.util.ArrayList;
import java.util.List;

public class ControllerPersonale {
    private List<Personale> list;

    public ControllerPersonale(){
        list=LaTazzaApplication.dao.getAll(Personale.class);//inizializza il campo list facendo query sul dataBase
        if(list==null){//inizializzazione fallita...
            //todo cosa fare?
        }else{
            for (Personale i:list) {//seleziono solo quelli attivi
                if(!i.isAttivo()){
                    list.remove(i);
                }
            }
        }
    }

    public List<Personale> getCopyList(){
        List<Personale> l= new ArrayList<>(list);
        return l;
    }

    public void aggiungiPersonale(String nome, String cognome){
        Personale p=new Personale(nome,cognome);//può lanciare null pointer exception!
        if(list.contains(p)) return;//todo check
        if(LaTazzaApplication.dao.save(p)){
            list.add(p);
        }else{
            //todo cosa fare?
        }
        return;
    }


    public void licenziaPersonale(Personale p){
        if(!list.contains(p))return;//todo check
        p.setAttivo(false);
        if(!LaTazzaApplication.dao.update(p)){//se fallisce ripristino stato iniziale
            //todo undochanges() ---> la chiamata sarà p.undoChanges();
        }
        list.remove(p);
    }




}
