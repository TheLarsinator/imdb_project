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

    public void findCompanyWithMostMediaItemsInGenre(String genre){
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT company.CompanyName, mediaItem.Genre, COUNT(*) " +
                    "FROM company INNER JOIN mediaitem " +
                    "ON company.CompanyName=mediaitem.CompanyName " +
                    "WHERE mediaItem.genre='" + genre + "' " +
                    "GROUP BY companyName ORDER BY 3 DESC";
            System.out.println(sql);
            System.out.println(" ");
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println(rs.getString("companyname") + " creates the most mediaitems in the genre " + genre);
            }
            System.out.println("----------------------");

        } catch (Exception e) {
            System.out.println("db error during select of roles= "+e);
            System.out.println("Try again");
            return;
        }
    }

    public void printAllSeriesWithIdAndTitle(BufferedReader reader){
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Series INNER JOIN mediaitem ON Series.mediaitemid=mediaitem.mediaitemid";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("----------------------");
            while(rs.next()) {
                System.out.println(rs.getString("mediaitemid") + ": " + rs.getString("title"));
            }
            System.out.println("----------------------");

        } catch (Exception e) {
            System.out.println("db error during select of series= "+e);
            System.out.println("Try again");
            return;
        }
    }

    public void printAllEpisodesInSeriesWithIdAndTitle(BufferedReader reader, int seriesID){
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Episode INNER JOIN mediaitem ON Episode.mediaitemid=mediaitem.mediaitemid where seriesid=" + seriesID;
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("----------------------");
            while(rs.next()) {
                System.out.println(rs.getString("mediaitemid") + ": " + rs.getString("title"));
            }
            System.out.println("----------------------");
        } catch (Exception e) {
            System.out.println("db error during select of episodes= "+e);
            System.out.println("Try again");
            return;
        }
    }
}
