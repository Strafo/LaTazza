package presentationLayer.guiLogicPkg.commandPkg;

public class RegistraVenditaCommand implements Command {

    private String tipoCialda;
    private boolean contanti;
    private int qta;
    private String nome;
    private String cognome;
    private boolean personale;//true se personale,false se visitatore.

    public RegistraVenditaCommand(String tipoCialda,boolean contanti,String nome,String cognome,int qta,boolean personale){
        this.contanti=contanti;
        this.nome=nome;
        this.cognome=cognome;
        this.qta=qta;
    }

    @Override
    public boolean execute() {
        return false;
    }
}

