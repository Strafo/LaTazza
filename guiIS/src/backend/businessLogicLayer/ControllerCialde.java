package backend.businessLogicLayer;

import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import presentationLayer.guiLogicPkg.LaTazzaApplication;
import java.util.List;

public  final class ControllerCialde {

    private ControllerCialde(){}

    public static  List<CialdeEntry> getCialdeEntryList(){
        return LaTazzaApplication.dao.getAll(CialdeEntry.class);
    }


}
