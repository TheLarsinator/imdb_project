package moviedb.tables;

import java.sql.Connection;
import java.sql.Statement;

public class Movie extends MediaItem {
    String producedFor;
    int length;

    public Movie(String title, String releaseDate, String storyline, String genre, String producedFor, int length) {
        super(title, releaseDate, storyline, genre);
        this.producedFor = producedFor;
        this.length = length;
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
            String sqlStmt = "insert into Movie values ("+ mediaItemID + "," + length + ",'"+producedFor+"')";
            stmt.executeUpdate(sqlStmt);
            System.out.println(sqlStmt);
        } catch (Exception e) {
            System.out.println("db error during insert of Movie="+e);
            return;
        }
    }
}
