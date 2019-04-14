package presentationLayer.guiLogicPkg.commandPkg;

import backend.dataAccessLayer.rowdatapkg.CialdeEntry;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Cliente;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Personale;
import backend.dataAccessLayer.rowdatapkg.clientPkg.Visitatore;
import presentationLayer.guiLogicPkg.BackEndInvoker;

public class RegistraVenditaCommand implements Command {

    private String tipoCialda;
    private boolean contanti;
    private int qta;
    private String nome;
    private String cognome;
    private boolean personale;//true se personale,false se visitatore.
    private BackEndInvoker backEndInvoker;

    public RegistraVenditaCommand(String tipoCialda,boolean contanti,String nome,String cognome,int qta,boolean personale,BackEndInvoker backEndInvoker){
        this.contanti=contanti;
        this.nome=nome;
        this.cognome=cognome;
        this.qta=qta;
        this.tipoCialda=tipoCialda;
        this.personale=personale;
        this.backEndInvoker=backEndInvoker;
    }

    @Override
    public boolean execute() {
        Cliente cliente= personale?new Personale(nome,cognome):new Visitatore(nome,cognome);
        CialdeEntry cialda=backEndInvoker.getControllerCialde().getCialda(tipoCialda);
        boolean result= backEndInvoker.getControllerContabilita().registraVendita(
                cliente,
                cialda,
                qta,
                contanti
        );
        return result;

    }
}

