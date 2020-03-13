package moviedb.tables;

import java.sql.Connection;
import java.sql.Statement;

public class Episode extends MediaItem {
    int episodeNr;
    int seasonNr;
    int seriesID;

    public Episode(String title, String releaseDate, String storyline, String genre, String mediaType, String companyName, int episodeNr, int seasonNr, int seriesID) {
        super(title, releaseDate, storyline, genre, mediaType, companyName);
        this.episodeNr = episodeNr;
        this.seasonNr = seasonNr;
        this.seriesID = seriesID;
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
            String sqlStmt = "insert into Episode values ("+ mediaItemID + ","+seriesID+"," + episodeNr + "," + seasonNr + ")";
            stmt.executeUpdate(sqlStmt);
        } catch (Exception e) {
            System.out.println("db error during insert of Episode="+e);
            return;
        }
    }

    @Override
    public void update(Connection conn){

    }
}
