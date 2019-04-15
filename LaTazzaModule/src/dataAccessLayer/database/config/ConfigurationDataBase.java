package dataAccessLayer.database.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dataAccessLayer.database.DatabaseConnectionHandler;

public class ConfigurationDataBase {

    private DatabaseConnectionHandler databaseConnectionHandler;


    public ConfigurationDataBase(DatabaseConnectionHandler database) {
        databaseConnectionHandler = database;
    }

    /**
     * Controlla se il database è già configurato.
     * @return true se già configurato,false altrimenti.
     */
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


    public void createSchema() {
        try {
            updateTable(confiDatabaseSchemaString);
        } catch (Exception e) {
            throw  new Error("Impossibile configurare database",e);
        }
    }


    public void initTriggers()  {
        try {
            TriggerCheckNumCialde.initTrigger(databaseConnectionHandler.getConnection());
            TriggerCompraVisitatore.initTrigger(databaseConnectionHandler.getConnection());
            ViewMagazzino.initView(databaseConnectionHandler.getConnection());
            ViewDebito.initView(databaseConnectionHandler.getConnection());
            ViewCassa.initView(databaseConnectionHandler.getConnection());
        } catch (SQLException e) {
            throw  new Error("Impossibile inizializzare triggers database",e);
        }
    }

    /**
     * @brief Se viene passato true esegue alcuni insseriment nel db per la presentazione del progetto.
     */
    public  void inserimentiTabellePresentazione(boolean eseguireInserimenti) {
        if(eseguireInserimenti){
            try {
                updateTable(inserimentiPresentazione);
            } catch (Exception e) {
                throw  new Error("Impossibile inserire inserimentiPresentazione ",e);
            }
        }
    }


    /**PRIVATE METHODS**/


    private void updateTable(String queryes) throws SQLException {
        PreparedStatement stmt;
        Connection connection = databaseConnectionHandler.getConnection();
        stmt = connection.prepareStatement(queryes);
        stmt.execute();stmt.close();
    }



    private static final String confiDatabaseSchemaString =
            "create schema LATAZZASCHEMA;" +

            "create table LATAZZASCHEMA.cialde(" +
            "  tipo varchar(64) not null primary key," +
            "  prezzo_euro int not null default (0) check ( prezzo_euro >=0)," +
            "  prezzo_centesimi int not null default (50) check( prezzo_centesimi >= 0 and prezzo_centesimi <100)" +
            ");" +

            "create table LATAZZASCHEMA.visitatore(" +
            "  nome varchar(64) not null," +
            "  cognome varchar(64) not null," +
            "  primary key(nome, cognome)" +
            ");" +


            "create table LATAZZASCHEMA.rifornimento(" +
            "" +
            "  dataR TIMESTAMP default CURRENT_TIMESTAMP not null," +
            "  numero_cialde integer not null,"+
            "  tipo_cialda varchar(64) not null references LATAZZASCHEMA.cialde(tipo) on update cascade on delete restrict," +
            "  primary key (dataR,tipo_cialda)" +
            ");" +

            "create table LATAZZASCHEMA.personale(" +
            "  nome varchar(64) not null," +
            "  cognome varchar(64) not null," +
            "  attivo boolean not null," +
            "  primary key (nome, cognome)" +
            ");" +

            "create table LATAZZASCHEMA.pagamento_debito(" +
            "" +
            "  nome varchar(64) not null," +
            "  cognome varchar(64) not null," +
            "  data TIMESTAMP default CURRENT_TIMESTAMP not null," +
            "  euro bigint not null check( euro > 0)," +
            "  centesimi int not null check(centesimi>=0 and centesimi < 100)," +
            "  primary key (nome, cognome, data)," +
            "  foreign key(nome, cognome) references LATAZZASCHEMA.personale(nome,cognome) on update cascade on delete restrict" +
            ");" +

            "create table LATAZZASCHEMA.compra_visitatore(" +
            "" +
            "  nome varchar(64) not null," +
            "  cognome varchar(64) not null," +
            "  tipo_cialda varchar(64) not null references LATAZZASCHEMA.cialde(tipo) on update cascade on delete restrict," +
            "  numero_cialde integer not null check (numero_cialde > 0)," +
            "  data TIMESTAMP default CURRENT_TIMESTAMP not null," +
            "  primary key(nome,cognome, data)," +
            "  foreign key(nome, cognome) references LATAZZASCHEMA.visitatore(nome, cognome) on update cascade on delete restrict" +
            ");" +

            "create table LATAZZASCHEMA.compra_dipendente(" +
            "" +
            "  nome varchar(64) not null," +
            "  cognome varchar(64) not null," +
            "  tipo_cialda varchar(64) not null references LATAZZASCHEMA.cialde(tipo) on update cascade on delete restrict," +
            "  numero_cialde integer not null check (numero_cialde > 0)," +
            "  data TIMESTAMP default CURRENT_TIMESTAMP not null," +
            "  contanti boolean not null," +
            "  primary key (data, nome, cognome)," +
            "  foreign key (nome, cognome) references LATAZZASCHEMA.personale(nome, cognome)on update cascade on delete restrict" +
            "" +
            ");" +


            "create table LATAZZASCHEMA.Magazzino" +
            "(" +
            "  tipo varchar(64) not null primary key," +
            "  qta integer not null default(0)" +
            ");" +
            "" +
            "create table LATAZZASCHEMA.Debito(" +
            "" +
            "  nome varchar(64) not null," +
            "  cognome varchar(64) not null," +
            "  euro bigint not null check( euro >= 0)," +
            "  centesimi int not null check(centesimi>=0 and centesimi < 100)," +
            "  attivo boolean not null," +
            "  primary key (nome,cognome)" +
            "" +
            ");" +

            "create table LATAZZASCHEMA.Cassa(" +
            "  euro bigint not null default (500)check( euro >= 0)," +
            "  centesimi int not null default (0) check(centesimi>=0 and centesimi < 100)" +
            ");" +
            "SET LOCK_MODE 1;" +
            "insert into LATAZZASCHEMA.Cassa values ();";

    private static final String inserimentiPresentazione="" +
            "insert into LATAZZASCHEMA.CIALDE values ('the', 0,50 );" +
            "insert into LATAZZASCHEMA.CIALDE values('caffe',0,50);" +
            "insert into LATAZZASCHEMA.CIALDE values('camomilla',0,50);" +
            "insert into LATAZZASCHEMA.VISITATORE values ( 'Andrea', 'Manzi' );" +
            "insert into LATAZZASCHEMA.VISITATORE values ( 'Edoardo', 'Vignola' );" +
            "insert into LATAZZASCHEMA.VISITATORE values ( 'Marco', 'Rigardo' );" +
            "insert into LATAZZASCHEMA.VISITATORE values ( 'Filippo', 'Carrubba' );" +
            "insert into LATAZZASCHEMA.PERSONALE values ( 'Jacopo', 'Dapueto', true );" +
            "insert into LATAZZASCHEMA.PERSONALE values ( 'Andrea', 'Straforini', true );" +
            "insert into LATAZZASCHEMA.PERSONALE values ( 'Simone', 'Campisi', true );" +
            "insert into LATAZZASCHEMA.PERSONALE values ( 'Gabriele', 'Armanino', true );" +
            "insert into LATAZZASCHEMA.RIFORNIMENTO values ('2019-01-12 12:00:00', 200,'caffe');" +
            "insert into LATAZZASCHEMA.RIFORNIMENTO values ('2018-05-11 12:00:00', 150,'the');" +
            "commit;"+
            "insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Simone','Campisi', 'caffe',13,'2018-06-12 13:00:00',true);" +

            "insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Gabriele','Armanino', 'the',8,'2018-06-11 14:00:00',false);" +
            "insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Simone','Campisi', 'caffe',13,'2018-06-10 13:00:00',false);" +
            "insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Andrea','Straforini', 'caffe',13,'2018-09-11 13:00:00',false);" +
            "insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Simone','Campisi', 'caffe',13,'2018-06-08 13:00:00',false);" +
            "insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Jacopo','Dapueto', 'caffe',15,'2018-08-07 16:30:00',false);" +
            "insert into LATAZZASCHEMA.COMPRA_DIPENDENTE values ('Jacopo','Dapueto', 'caffe',20,'2018-07-06 9:00:00', false);" +

            "insert into LATAZZASCHEMA.COMPRA_VISITATORE values ('Andrea','Manzi', 'caffe',10,'2018-07-10 8:00:00' );" +
            "insert into LATAZZASCHEMA.COMPRA_VISITATORE values ('Edoardo','Vignola','the',12,'2019-02-15 17:00:00');" +
            "insert into LATAZZASCHEMA.COMPRA_VISITATORE values ('Andrea','Manzi', 'caffe',2000,'2018-07-10 9:00:00');" +
            "insert into LATAZZASCHEMA.PAGAMENTO_DEBITO values ('Jacopo','Dapueto', '2019-01-11 14:00:00',2, 33);" +
            "insert into LATAZZASCHEMA.PAGAMENTO_DEBITO values ('Jacopo','Dapueto', '2019-02-11 14:00:00',5, 66);";

}