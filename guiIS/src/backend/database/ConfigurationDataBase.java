package backend.database;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import backend.database.config.*;
import utils.PathHandler;


public class ConfigurationDataBase {//todo rimuovere le cose che non servono

    private static DatabaseConnectionHandler databaseConnectionHandler;
    private static final String userDir=System.getProperty("user.dir");
    private static final String createSchemaFile="databaseConfig.sql";
    private static final String insertFile="insertConfig.sql";
    private static final String PATHConfig="\\guiIS\\src\\backend\\database\\config\\";

    public ConfigurationDataBase(DatabaseConnectionHandler database) {
        databaseConnectionHandler = database;
    }

    public boolean existsSchema() {
        PreparedStatement stat;
        ResultSet rs;
        Connection connection = databaseConnectionHandler.getConnection();
        try {
            //faccio una select su una delle tabelle dello schema, se non c'è vuol dire che non è stato creato lo schema
            stat = connection.prepareStatement("SELECT * from LATAZZASCHEMA.CIALDE ");
            rs = stat.executeQuery();
            rs.next();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    private void updateTable() {
        Connection connection = databaseConnectionHandler.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement(confiDatabaseSchemaString);
            stmt.execute();
            stmt.close();
            System.out.println("CREAZIONE SCHEMA AVVENUTA CON SUCCESSO!");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

    public void createSchema() throws SQLException {
        updateTable();
    }


    public void initTriggers() throws SQLException {

        TriggerCheckNumCialde.initTrigger(databaseConnectionHandler.getConnection());
        TriggerCompraVisitatore.initTrigger(databaseConnectionHandler.getConnection());
        ViewMagazzino.initView(databaseConnectionHandler.getConnection());
        ViewDebito.initView(databaseConnectionHandler.getConnection());
        ViewCassa.initView(databaseConnectionHandler.getConnection());
    }

    private void updateTable(String sqlFileName)  {
        Scanner inFile=null;
        Connection connection=databaseConnectionHandler.getConnection();
        try {

            StringBuilder file= new StringBuilder();
            System.out.println(PathHandler.modifyPath(userDir+PATHConfig+sqlFileName));
            inFile= new Scanner(new FileReader(PathHandler.modifyPath(userDir+PATHConfig+sqlFileName)));

            while(inFile.hasNext()) file.append(inFile.nextLine()).append("\n");

            PreparedStatement stmt=connection.prepareStatement(file.toString());
            stmt.execute();
            stmt.close();
            System.out.println("CREAZIONE INSERIMENTI AVVENUTA CON SUCCESSO!");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);


        }finally {
            inFile.close();
        }

    }


    public  void inserimentiIniziali() throws SQLException{
        updateTable(insertFile);
    }

    private static final String confiDatabaseSchemaString =
            "create schema LATAZZASCHEMA;\n" +
            "\n" +
            "create table LATAZZASCHEMA.cialde(\n" +
            "  tipo varchar(64) not null primary key,\n" +
            "  prezzo_euro int not null default (0) check ( prezzo_euro >=0),\n" +
            "  prezzo_centesimi int not null default (50) check( prezzo_centesimi >= 0 and prezzo_centesimi <100)\n" +
            ");\n" +
            "\n" +
            "\n" +
            "create table LATAZZASCHEMA.visitatore(\n" +
            "  nome varchar(64) not null,\n" +
            "  cognome varchar(64) not null,\n" +
            "  primary key(nome, cognome)\n" +
            ");\n" +
            "\n" +
            "create table LATAZZASCHEMA.rifornimento(\n" +
            "\n" +
            "  dataR TIMESTAMP default CURRENT_TIMESTAMP not null,\n" +
            "  numero_cialde integer not null, -- NUMERO DI cialde COMPRATE\n" +
            "  tipo_cialda varchar(64) not null references LATAZZASCHEMA.cialde(tipo) on update cascade on delete restrict,\n" +
            "  primary key (dataR,tipo_cialda)\n" +
            ");\n" +
            "\n" +
            "create table LATAZZASCHEMA.personale(\n" +
            "  nome varchar(64) not null,\n" +
            "  cognome varchar(64) not null,\n" +
            "  attivo boolean not null,\n" +
            "  primary key (nome, cognome)\n" +
            ");\n" +
            "\n" +
            "create table LATAZZASCHEMA.pagamento_debito(\n" +
            "\n" +
            "  nome varchar(64) not null,\n" +
            "  cognome varchar(64) not null,\n" +
            "  data TIMESTAMP default CURRENT_TIMESTAMP not null,\n" +
            "  euro bigint not null check( euro > 0),\n" +
            "  centesimi int not null check(centesimi>=0 and centesimi < 100),\n" +
            "  primary key (nome, cognome, data),\n" +
            "  foreign key(nome, cognome) references LATAZZASCHEMA.personale(nome,cognome) on update cascade on delete restrict\n" +
            ");\n" +
            "\n" +
            "create table LATAZZASCHEMA.compra_visitatore(\n" +
            "\n" +
            "  nome varchar(64) not null,\n" +
            "  cognome varchar(64) not null,\n" +
            "  tipo_cialda varchar(64) not null references LATAZZASCHEMA.cialde(tipo) on update cascade on delete restrict,\n" +
            "  numero_cialde integer not null check (numero_cialde > 0),\n" +
            "  data TIMESTAMP default CURRENT_TIMESTAMP not null,\n" +
            "  primary key(nome,cognome, data),\n" +
            "  foreign key(nome, cognome) references LATAZZASCHEMA.visitatore(nome, cognome) on update cascade on delete restrict\n" +
            ");\n" +
            "\n" +
            "create table LATAZZASCHEMA.compra_dipendente(\n" +
            "\n" +
            "  nome varchar(64) not null,\n" +
            "  cognome varchar(64) not null,\n" +
            "  tipo_cialda varchar(64) not null references LATAZZASCHEMA.cialde(tipo) on update cascade on delete restrict,\n" +
            "  numero_cialde integer not null check (numero_cialde > 0),\n" +
            "  data TIMESTAMP default CURRENT_TIMESTAMP not null,\n" +
            "  contanti boolean not null, -- se � false paga con credito, altrimenti in contanti\n" +
            "  primary key (data, nome, cognome),\n" +
            "  foreign key (nome, cognome) references LATAZZASCHEMA.personale(nome, cognome)on update cascade on delete restrict\n" +
            "\n" +
            ");\n" +
            "\n" +
            "\n" +
            "create table LATAZZASCHEMA.Magazzino\n" +
            "(\n" +
            "  tipo varchar(64) not null primary key,\n" +
            "  qta integer not null default(0)\n" +
            ");\n" +
            "\n" +
            "create table LATAZZASCHEMA.Debito(\n" +
            "\n" +
            "  nome varchar(64) not null,\n" +
            "  cognome varchar(64) not null,\n" +
            "  euro bigint not null check( euro >= 0),\n" +
            "  centesimi int not null check(centesimi>=0 and centesimi < 100),\n" +
            "  attivo boolean not null,\n" +
            "  primary key (nome,cognome)\n" +
            "\n" +
            ");\n" +
            "\n" +
            "create table LATAZZASCHEMA.Cassa(\n" +
            "  euro bigint not null default (500)check( euro >= 0),\n" +
            "  centesimi int not null default (0) check(centesimi>=0 and centesimi < 100)\n" +
            ");\n" +
            "SET LOCK_MODE 1;\n" +
            "insert into LATAZZASCHEMA.Cassa values ();\n";

}