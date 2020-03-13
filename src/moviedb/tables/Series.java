package moviedb.tables;

import java.sql.Connection;
import java.sql.Statement;

public class Series extends MediaItem {
    int startYear;
    int endYear;

    public Series(String title, String releaseDate, String storyline, String genre, String mediaType, int startYear, String companyName) {
        super(title, releaseDate, storyline, genre, mediaType, companyName);
        this.startYear = startYear;
    }

    public void endSeries(int endYear){
        this.endYear = endYear;
    }

    @Override
    public void initialize(Connection conn) {
        super.initialize(conn);
    }

    @Override
    public void refresh(Connection conn) {
        initialize(conn);
    }

    @Override
    public void save(Connection conn) {
        super.save(conn);
        try {
            Statement stmt = conn.createStatement();
            String sqlStmt = "insert into Series values ("+ mediaItemID + ","+startYear+"," + endYear + ")";
            stmt.executeUpdate(sqlStmt);
            System.out.println(sqlStmt);
        } catch (Exception e) {
            System.out.println("db error during insert of Series="+e);
            return;
        }
    }

    @Override
    public void update(Connection conn){
        super.update(conn);
        try {
            Statement stmt = conn.createStatement();
            String sqlStmt = "UPDATE Series set startYear="+startYear+", endYear=" + endYear + " where mediaItemID=" + mediaItemID;
            System.out.println(sqlStmt);
            stmt.executeUpdate(sqlStmt);
        } catch (Exception e) {
            System.out.println("db error during update of Series="+e);
            return;
        }
    }
}
