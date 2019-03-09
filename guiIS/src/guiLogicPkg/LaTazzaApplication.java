package guiLogicPkg;

public class LaTazzaApplication implements Runnable {

	private LaTazzaFrame laTazzaFrame;//Finestra dell'applicazione


    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new LaTazzaApplication());


    }

    public void run(){
        this.initFrame();

    }


    private void initFrame(){
        laTazzaFrame=new LaTazzaFrame();
        laTazzaFrame.setVisible(true);
        laTazzaFrame.setLocationCenter();

    }

}
