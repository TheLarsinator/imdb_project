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

    public void listAllMoviesForAnActor(String name){
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT actorinmedia.name, mediaitem.Title FROM actorinmedia INNER JOIN mediaitem " +
                    "ON actorinmedia.MediaItemID=mediaitem.MediaItemID " +
                    "WHERE mediaitem.mediatype='Movie' and actorinmedia.name='"+ name + "'";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(name + " plays in the following movies:");
            while (rs.next()) {
                System.out.println(rs.getString("title"));
            }
            System.out.println("----------------------");

        } catch (Exception e) {
            System.out.println("db error during select of roles= "+e);
            System.out.println("Try again");
            return;
        }
    }
}
