package backend.businessLogicLayer;

import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import java.util.List;

public  final class ControllerCialde {

    private ControllerCialde(){}

    public static  List<CialdeEntry> getCialdeEntryList(){
        return LaTazzaApplication.dao.getAll(CialdeEntry.class);
    }

    public static  CialdeEntry getCialda(String tipo){
        List<CialdeEntry> lista=getCialdeEntryList();
        for (CialdeEntry i:lista) {
            if(i.getTipo().equalsIgnoreCase(tipo))
                return new CialdeEntry(i.getTipo(),i.getPrezzo());
        }
        return null;
    }


}
