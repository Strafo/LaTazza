package presentationLayer.guiLogicPkg;
import presentationLayer.guiConfig.structurePanelsPropertiesPkg.LaTazzaFrameProperties;
import presentationLayer.guiLogicPkg.commandPkg.InitBackEndCommand;
import presentationLayer.guiLogicPkg.commandPkg.NotifyObserversCommand;
import java.util.Arrays;
import java.util.List;

public  class LaTazzaApplication implements Runnable {

    private LaTazzaFrame laTazzaFrame;//Finestra dell'applicazione
    public static BackEndInvoker backEndInvoker;


    public static void main(String[] args)  {
        java.awt.EventQueue.invokeLater(new LaTazzaApplication());

    }

    public void run(){
        this.initBackEnd();
        this.initFrame();
        List<ObserverSubscriptionType> subList=Arrays.asList(ObserverSubscriptionType.values());
        backEndInvoker.executeCommand(new NotifyObserversCommand(subList,backEndInvoker));
    }

    private void initBackEnd(){
        backEndInvoker=new BackEndInvoker();
        if(!backEndInvoker.executeCommand(new InitBackEndCommand(backEndInvoker))){
            throw  new Error("Impossibile avviare applicazione");
        }
    }

	private void initFrame(){
        laTazzaFrame=new LaTazzaFrame();
        (new LaTazzaFrameProperties()).initFrame(laTazzaFrame);
        laTazzaFrame.setVisible(true);
        laTazzaFrame.setLocationCenter();
    }



}
