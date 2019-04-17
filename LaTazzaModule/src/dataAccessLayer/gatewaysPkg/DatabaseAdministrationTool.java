package dataAccessLayer.gatewaysPkg;

import businessLogicLayer.commandPkg.InitBackEndCommand;
import dataAccessLayer.database.DatabaseConnectionHandler;
import dataAccessLayer.gatewaysPkg.receiverPkg.*;
import dataAccessLayer.rowdatapkg.*;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;

public class DatabaseAdministrationTool {
    private DatabaseConnectionHandler databaseConnectionHandler;
    private DaoInvoker dao;
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private enum DbATCommands{
        STARTTRANSACTION,
        ENDTRANSACTION,
        GETTRANSACTIONSTATUS,
        QUERY,
        REFRESH,
        EXIT,
        UNKNOWNCOMMAND,
        HELP
    }


    private static final String MENU=
            "\nCOMANDI:\n\t" +
                    DbATCommands.STARTTRANSACTION+":Inizia Transazione\n\t"+
                    DbATCommands.ENDTRANSACTION+":Termina Transazione\n\t"+
                    DbATCommands.GETTRANSACTIONSTATUS+"Ottiene stato Transazione\n\t"+
                    DbATCommands.QUERY+":Scrivi query o comandi\n\t" +
                    DbATCommands.REFRESH+":Pulisci scheramata\n\t"+
                    DbATCommands.HELP+":lista comandi\n\t"+
                    DbATCommands.EXIT+":termina sessione\n";

    private static final String TEAMNAME=ANSI_RED+
            "    _   _               ____                      ____       _ _        \n" +
                    "   | \\ | | ___  _ __   / ___|  ___  _ __   ___   | __ )  ___| | | ___   \n" +
                    "   |  \\| |/ _ \\| '_ \\  \\___ \\ / _ \\| '_ \\ / _ \\  |  _ \\ / _ \\ | |/ _ \\  \n" +
                    "   | |\\  | (_) | | | |  ___) | (_) | | | | (_) | | |_) |  __/ | | (_) | \n" +
                    "   |_| \\_|\\___/|_| |_| |____/ \\___/|_| |_|\\___/  |____/ \\___|_|_|\\___/  \n" +
                    "          |  \\/  | __ _                                                 \n" +
                    "          | |\\/| |/ _` |                                                \n" +
                    "          | |  | | (_| |                                                \n" +
                    "     ____ |_|  |_|\\__,_|_             _____                             \n" +
                    "    |  _ \\ __ _| |_ ___| |__   ___   |_   _|__  __ _ _ __ ___           \n" +
                    "    | |_) / _` | __/ __| '_ \\ / _ \\    | |/ _ \\/ _` | '_ ` _ \\          \n" +
                    "    |  __/ (_| | || (__| | | | (_) |   | |  __/ (_| | | | | | |         \n" +
                    "    |_|   \\__,_|\\__\\___|_| |_|\\___/    |_|\\___|\\__,_|_| |_| |_|         \n" +
                    "                                                                        \n" +
                    "\n"+ANSI_RESET;

    private static Collection
            <Pair<Class<? extends AbstractEntryDB>,Class<? extends AbstractDaoReceiver>>> daoCollection
            =InitBackEndCommand.daoCollection;


    public DatabaseAdministrationTool(String dbpath) throws SQLException, ClassNotFoundException {
        databaseConnectionHandler=new DatabaseConnectionHandler(dbpath);
        databaseConnectionHandler.initDataBase();
        dao=new DaoInvoker(databaseConnectionHandler.getConnection(),daoCollection);
    }


    private void printMenu(){
        System.out.println(ANSI_RED+MENU+ANSI_RESET);
    }

    @Override
    public void finalize(){
        try {
            this.databaseConnectionHandler.closeDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DbATCommands parseCommand(){
        Scanner scanner = new Scanner(System. in);
        String inputString = scanner. nextLine();
        if(inputString.equalsIgnoreCase(String.valueOf(DbATCommands.STARTTRANSACTION))){
            return DbATCommands.STARTTRANSACTION;
        }
        if(inputString.equalsIgnoreCase(String.valueOf(DbATCommands.ENDTRANSACTION))){
            return DbATCommands.ENDTRANSACTION;
        }
        if(inputString.equalsIgnoreCase(String.valueOf(DbATCommands.GETTRANSACTIONSTATUS))){
            return DbATCommands.GETTRANSACTIONSTATUS;
        }
        if(inputString.equalsIgnoreCase(String.valueOf(DbATCommands.QUERY))){
            return DbATCommands.QUERY;
        }
        if(inputString.equalsIgnoreCase(String.valueOf(DbATCommands.REFRESH))){
            return DbATCommands.REFRESH;
        }
        if(inputString.equalsIgnoreCase(String.valueOf(DbATCommands.EXIT))){
            return DbATCommands.EXIT;
        }
        if(inputString.equalsIgnoreCase(String.valueOf(DbATCommands.HELP))){
            return DbATCommands.HELP;
        }
        return DbATCommands.UNKNOWNCOMMAND;
    }


    public void executeCommand(DbATCommands c)  {
        switch (c){
            case STARTTRANSACTION:
                dao.startTransaction();
                System.out.println("transazione avviata\n");
                break;
            case ENDTRANSACTION:
                dao.endTransaction();
                System.out.println("transazione terminata "+(dao.getTransactionStatus()?" con successo":"senza successo"));
                break;
            case GETTRANSACTIONSTATUS:
                System.out.println("stato transazione:"+(dao.getTransactionStatus()?"corretta":"fallita"));
                break;
            case QUERY:
                if(!dao.executeStatement(getString())){
                    System.err.println("ERRORE consultare LaTazza.log");
                }
                break;
            case REFRESH:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                break;
            case UNKNOWNCOMMAND:
                System.out.println("Comando non riconosciuto...");
                break;
            case EXIT:
                break;
            case HELP:
                printMenu();
                break;
        }
    }

    public String getString(){
        Scanner input = new Scanner(System.in);
        StringBuilder text= new StringBuilder();
        while (true) {
            String temp = input.nextLine();
            if (temp.isEmpty()) break;
            text.append(temp);
        }
        return text.toString();
    }

    private String buildTitle() throws UnknownHostException {
        return ANSI_GREEN+System.getProperty("user.name")+"@"+ANSI_CYAN+InetAddress.getLocalHost().getHostName()
        +" "+System.getProperty("user.dir")+" $"+ANSI_RESET;
    }

    public static void main(String[] args) {
        DatabaseAdministrationTool dbat;
        DbATCommands comando;
        String[] path;
        if(args.length!=1){System.out.println("inserire path del file database (*.mv.db)");return;}
        File f = new File(args[0]);
        if(!f.exists() || f.isDirectory()) {
          System.out.println("file "+args[0]+" non trovato.");
          return;
        }
        path=args[0].split("\\.mv\\.db");
        System.out.println("Powered by\n\n"+TEAMNAME+"\n\n\n");
        try{
            dbat=new DatabaseAdministrationTool(path[0]);
            dbat.printMenu();
            do {
                System.out.print( dbat.buildTitle());
                comando=dbat.parseCommand();
                dbat.executeCommand(comando);
            }while(comando!=DbATCommands.EXIT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
