package gui;

public class LaTazzaApplication implements Runnable {

	private LaTazzaFrame laTazzaFrame;//app window


    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new LaTazzaApplication());


    }

    public void run(){
        this.initFrame();

        

    }
    /**Inizialize app window and make it visible**/
	private void initFrame(){
        laTazzaFrame=new LaTazzaFrame();
        laTazzaFrame.setVisible(true);
        laTazzaFrame.setLocationCenter();

    }

}
