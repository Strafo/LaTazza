package presentationLayer.guiLogicPkg;

import backend.businessLogicLayer.ControllerContabilita;
import backend.businessLogicLayer.ControllerPersonale;
import backend.dataAccessLayer.gatewaysPkg.DaoInterface;
import backend.dataAccessLayer.gatewaysPkg.DaoManager;
import backend.database.DataBase;

import java.sql.SQLException;

public  class LaTazzaApplication implements Runnable {

	private LaTazzaFrame laTazzaFrame;//Finestra dell'applicazione
    public static DataBase dataBase;
    public static DaoInterface dao;
    public static ControllerContabilita controllerContabilita;
    public static ControllerPersonale controllerPersonale;

    public static void main(String[] args)  {
        java.awt.EventQueue.invokeLater(new LaTazzaApplication());

    }

    public void run(){
        dataBase=new DataBase();//inizializzo dataBase
        try {
            dataBase.initDataBase();
        } catch (SQLException| ClassNotFoundException e) {
            e.printStackTrace();//todo fare una migliore gestione degli errori
            System.exit(1);
        }
        dao=new DaoManager(dataBase.getConnection());
        controllerPersonale=new ControllerPersonale();
        controllerContabilita=new ControllerContabilita();
        this.initFrame();

    }



	private void initFrame(){
        laTazzaFrame=new LaTazzaFrame();
        laTazzaFrame.setVisible(true);
        laTazzaFrame.setLocationCenter();

    }



}
