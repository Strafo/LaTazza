package presentationLayer.guiLogicPkg.commandPkg;


import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import presentationLayer.guiLogicPkg.BackEndInvoker;

public class RegistraRifornimentoCommand implements  Command{

    private String tipoCialda;
    private int qta;
    private BackEndInvoker backEndInvoker;

    public RegistraRifornimentoCommand(String tipoCialda, int qta){
        this.tipoCialda=tipoCialda;
        this.qta=qta;
         this.backEndInvoker=backEndInvoker;
   }

    @Override
    public boolean execute() {
        CialdeEntry cialda= backEndInvoker.getControllerCialde().getCialda(tipoCialda);
        boolean result= backEndInvoker.getControllerContabilita().registrareRifornimento(cialda,qta);
        return result;
    }
}
