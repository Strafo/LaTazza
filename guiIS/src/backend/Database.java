package backend;
import java.sql.*;

public class Database {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:file:/home/strafo/Scrivania/Unige/terzo_anno/ing_software/LaTazza/guiIS/src/database/persistence;DB_CLOSE_DELAY=-1;";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";

    private Connection con;

    public Database(){

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            con=DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);

        }

    }

    public void checkTipoCialdaTable(){

        for(TipoCialda i:TipoCialda.values()){

        }
        Statement st= null;


        try {


            st = con.createStatement();





            ResultSet rs=st.executeQuery("select CURRENT_USER ");
            this.printData(rs);

            //System.exit(1);
            //st.execute("GRANT ALL ON SCHEMA DBLATAZZA TO PUBLIC ");

            rs=st.executeQuery("SELECT * FROM PUBLIC.CIALDE");
            this.printData(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);

        }

        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        Database database=new Database();
        database.checkTipoCialdaTable();

    }

    public void printData(ResultSet resultSet){
        ResultSetMetaData rsmd ;
        try {
            rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
