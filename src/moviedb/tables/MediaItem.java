package moviedb.tables;

import moviedb.ActiveDomainObject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public class MediaItem extends ActiveDomainObject {

    public int mediaItemID; // KEY
    String title;
    int releaseYear;
    Date releaseDate;
    String storyline;
    String genre;
    String companyName;
    int soundtrackID;
    String mediaType;

    private static int NO_ID = -1;
    private static String NO_NAME = "NO_NAME";

    public MediaItem(String title, String releaseDate, String storyline, String genre, String mediaType){
        this.mediaItemID = NO_ID;
        this.title = title;
        this.releaseDate = Date.valueOf(releaseDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.releaseDate);
        this.releaseYear = cal.get(Calendar.YEAR);
        this.storyline = storyline;
        this.genre = genre;
        this.companyName = NO_NAME;
        this.soundtrackID = NO_ID;
        this.mediaType = mediaType;
    }

    @Override
    public void initialize(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Mediaitem where mediaID=" + mediaItemID);
            while (rs.next()) {
                this.title =  rs.getString("title");
                this.storyline =  rs.getString("storyline");
                this.genre =  rs.getString("genre");
                this.companyName =  rs.getString("companyName");
                this.releaseYear = rs.getInt("releaseYear");
                this.soundtrackID = rs.getInt("soundtrackID");
            }

        } catch (Exception e) {
            System.out.println("db error during select of mediaitem= "+e);
            return;
        }
    }

    @Override
    public void refresh(Connection conn) {
        initialize(conn);
    }

    @Override
    public void save(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            String sqlStmt = "insert into Mediaitem values (NULL,'"+title+"',"+releaseYear+",'"+releaseDate+"','"+storyline+"','"+genre+"',NULL,NULL,'"+ mediaType + "')"; //TODO: Add company and soundteck
            stmt.executeUpdate(sqlStmt, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys();
            if(keys.next())
                mediaItemID = keys.getInt(1);
        } catch (Exception e) {
            System.out.println("db error during insert of mediaitem="+e);
            return;
        }
    }

    @Override
    public void update(Connection conn) {

    }
}
