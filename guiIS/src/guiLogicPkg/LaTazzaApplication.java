package guiLogicPkg;

import database.DataBase;

import java.sql.SQLException;

public  class LaTazzaApplication implements Runnable {

	private LaTazzaFrame laTazzaFrame;//Finestra dell'applicazione
    public static DataBase dataBase;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new LaTazzaApplication());


    }

    public void run(){
        dataBase=new DataBase();
        try {
            dataBase.initDataBase();
        } catch (SQLException| ClassNotFoundException e) {
            e.printStackTrace();//todo fare una migliore gestione degli errori
        }
        this.initFrame();

    }



	private void initFrame(){
        laTazzaFrame=new LaTazzaFrame();
        laTazzaFrame.setVisible(true);
        laTazzaFrame.setLocationCenter();

    }

}
