package businessLogicLayer.commandPkg;


import dataAccessLayer.rowdatapkg.CialdeEntry;
import businessLogicLayer.BackEndInvoker;

public class RegistraRifornimentoCommand implements  Command{

    private String tipoCialda;
    private int qta;
    private BackEndInvoker backEndInvoker;

    public RegistraRifornimentoCommand(String tipoCialda, int qta, BackEndInvoker backEndInvoker){
        this.tipoCialda=tipoCialda;
        this.qta=qta;
        this.backEndInvoker=backEndInvoker;
   }

    @Override
    public LaTazzaErrno execute() {
        CialdeEntry cialda= backEndInvoker.getControllerCialde().getCialda(tipoCialda);
        return backEndInvoker.getControllerContabilita().registrareRifornimento(cialda,qta);
    }
}
