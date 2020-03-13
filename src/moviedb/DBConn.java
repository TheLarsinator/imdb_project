package moviedb;

import java.sql.*;
import java.util.Properties;

public abstract class DBConn {
    protected Connection conn;
    public DBConn () {
    }
    public void connect() {
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance(); //For old versions
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Properties for user and password.
            Properties p = new Properties();
            p.put("user", "root");
            p.put("password", "admin");
            //            conn = DriverManager.getConnection("jdbc:mysql://mysql.ansatt.ntnu.no/sveinbra_ektdb?autoReconnect=true&useSSL=false",p);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dev_database?autoReconnect=true&useSSL=false",p);
        } catch (Exception e)
        {
            System.out.println(e.toString());
            throw new RuntimeException("Unable to connect", e);
        }
    }
}