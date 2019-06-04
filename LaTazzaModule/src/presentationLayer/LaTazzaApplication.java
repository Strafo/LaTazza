package presentationLayer;

import businessLogicLayer.BackEndInvoker;
import businessLogicLayer.ObserverSubscriptionType;
import businessLogicLayer.commandPkg.Command;
import presentationLayer.guiConfig.structurePanelsPropertiesPkg.LaTazzaFrameProperties;
import businessLogicLayer.commandPkg.InitBackEndCommand;
import businessLogicLayer.commandPkg.NotifyObserversCommand;
import presentationLayer.guiLogicPkg.LaTazzaFrame;
import utils.LaTazzaLogger;
import java.util.Arrays;
import java.util.List;

public  class LaTazzaApplication implements Runnable {

    public static boolean MODALITAPRESENTAZIONE=false;
    private LaTazzaFrame laTazzaFrame;//Finestra dell'applicazione
    public static BackEndInvoker backEndInvoker;


    public static void main(String[] args)  {
            java.awt.EventQueue.invokeLater(new LaTazzaApplication(args.length==0?null:args[0]));
    }

    public LaTazzaApplication(String modalita){
        MODALITAPRESENTAZIONE="presentazione".equalsIgnoreCase(modalita);
    }

    public void run(){

        this.initLaTazzaLogger();
        this.initBackEnd();
        this.initFrame();
        List<ObserverSubscriptionType> subList=Arrays.asList(ObserverSubscriptionType.values());
        backEndInvoker.executeCommand(new NotifyObserversCommand(subList,backEndInvoker));
    }

    /**
     * Inizializza i controller e il dao.
     */
    private void initBackEnd(){
        backEndInvoker=new BackEndInvoker();
        if(backEndInvoker.executeCommand(new InitBackEndCommand(backEndInvoker,MODALITAPRESENTAZIONE))!=Command.LaTazzaErrno.NOERROR){
            throw  new Error("Impossibile avviare applicazione");
        }
    }

    /**
     * Inizializza i frame e tutti i pannelli della Gui.
     */
	private void initFrame(){
        laTazzaFrame=new LaTazzaFrame();
        (new LaTazzaFrameProperties()).initFrame(laTazzaFrame);
        laTazzaFrame.setVisible(true);
        laTazzaFrame.setLocationCenter();
    }

    /**
     * Inizializza il logger dell'applicazione.
     */
    private void initLaTazzaLogger(){
        LaTazzaLogger.initLogger(true);
    }




}
