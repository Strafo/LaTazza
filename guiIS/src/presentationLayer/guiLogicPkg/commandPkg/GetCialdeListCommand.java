package presentationLayer.guiLogicPkg.commandPkg;

import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import presentationLayer.guiLogicPkg.BackEndInvoker;

import java.util.List;
import java.util.ListIterator;

public class GetCialdeListCommand implements Command {

    private List<String> cialdeList;
    private BackEndInvoker backEndInvoker;

    public GetCialdeListCommand(BackEndInvoker backEndInvoker){
        this.backEndInvoker=backEndInvoker;
    }
    @Override
    public boolean execute() throws Exception {
        List<CialdeEntry>list=backEndInvoker.getControllerCialde().getCialdeEntryList();
        for(ListIterator<CialdeEntry> iter=list.listIterator();iter.hasNext();){
            cialdeList.add(iter.next().getTipo());
        }
        return true;
    }

    public List<String>getCialdeList(){return cialdeList;}

}
