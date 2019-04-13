package backend.businessLogicLayer;

import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import java.util.List;
import java.util.Observable;

public  final class ControllerCialde extends Observable {

    public List<CialdeEntry> listaCialde;

    public ControllerCialde(){
        if((listaCialde=LaTazzaApplication.backEndInvoker.getDao().getAll(CialdeEntry.class))==null){
            throw new Error(new Throwable("Impossibile creare controllerCialde nell'applicazione."));
        }
    }

    public List<CialdeEntry> getCialdeEntryList(){
        return listaCialde;
    }

    public  CialdeEntry getCialda(String tipo){
        for (CialdeEntry i:listaCialde) {
            if(i.getTipo().equalsIgnoreCase(tipo))
                return new CialdeEntry(i.getTipo(),i.getPrezzo());
        }
        return null;
    }

}
