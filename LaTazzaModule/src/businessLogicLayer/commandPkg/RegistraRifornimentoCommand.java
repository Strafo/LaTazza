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
    public boolean execute() {
        System.out.println(tipoCialda);

        CialdeEntry cialda= backEndInvoker.getControllerCialde().getCialda(tipoCialda);
        System.out.println(cialda.getTipo());
        boolean result= backEndInvoker.getControllerContabilita().registrareRifornimento(cialda,qta);
        return result;
    }
}
