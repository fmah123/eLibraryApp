package eLibraryApp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DB {
    static final String JDBC_DRIVER = "com.oracle.jdbc.Driver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";

    //Line 12 to 24:  This method returns the JDBC driver connection object.
    public static Connection getCon(){
        Connection con = null;
        try{
            Class.forName(JDBC_DRIVER);
            Properties info = new Properties();
            info.put("username", "system");
            info.put("password", "system");
            con = DriverManager.getConnection(DB_URL, info);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return con;
    }
}
