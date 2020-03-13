package moviedb;

import moviedb.tables.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

public class OutputDatabaseController extends DatabaseController {

    public OutputDatabaseController(){
        super();
    }

    public Person findPersonByName(String name){
        Person outPers = new Person(name);
        outPers.initialize(conn);
        System.out.println("Finding person status:");
        System.out.println(outPers.getName());
        System.out.println(outPers.getNationality());
        return outPers;
    }

    public void listAllRolesForAPerson(String name){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select role from ActorInMedia where name='" + name + "'");
            System.out.println(name + " plays the following roles:");
            while (rs.next()) {
                System.out.println(rs.getString("role"));
            }

        } catch (Exception e) {
            System.out.println("db error during select of roles= "+e);
            System.out.println("Try again");
            return;
        }
    }
}
