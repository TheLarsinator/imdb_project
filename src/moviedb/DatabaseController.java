package moviedb;

import moviedb.tables.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

public class DatabaseController extends DBConn {

    public DatabaseController(){
        connect();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of DatabaseController="+e);
            return;
        }
    }

    public boolean createEntry(ActiveDomainObject obj){
        obj.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of object="+e);
            return false;
        }
        return true;
    }

    public boolean updateEntry(ActiveDomainObject obj){
        obj.update(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of object="+e);
            return false;
        }
        return true;
    }

    protected static String getUserInput(BufferedReader reader){
        try{
            String out = reader.readLine();
            return out;
        } catch (IOException e){
            System.out.println("Failed to read user input");
            return "";
        }
    }
}
