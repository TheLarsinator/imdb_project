package moviedb.tables;

import moviedb.ActiveDomainObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MediaReview extends ActiveDomainObject {

    String username;
    int mediaItemID;
    int rating;
    String comment;

    public MediaReview(String username, int mediaItemID, int rating, String comment){
        this.username = username;
        this.mediaItemID = mediaItemID;
        this.rating = rating;
        this.comment = comment;
    }

    @Override
    public void initialize(Connection conn) {

    }

    @Override
    public void refresh(Connection conn) {
        initialize (conn);
    }

    @Override
    public void save(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            String sqlStmt = "insert into MediaReview values ('"+username+"'," + mediaItemID + "," + rating + ",'"+ comment +"')";
            stmt.executeUpdate(sqlStmt);
        } catch (Exception e) {
            System.out.println("db error during insert of MediaReview="+e);
            return;
        }
    }

    @Override
    public void update(Connection conn) {

    }
}
