package moviedb.tables;

import moviedb.ActiveDomainObject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class ActorInMedia extends ActiveDomainObject {

    int personID;
    int mediaItemID;
    public String role;

    public ActorInMedia(int personID, int mediaItemID, String role){
        this.personID = personID;
        this.mediaItemID = mediaItemID;
        this.role = role;
    }

    @Override
    public void initialize(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ActorInMedia where personID=" + personID + " and mediaItemID =" + mediaItemID);
            while (rs.next()) {
                this.role =  rs.getString("role");
            }

        } catch (Exception e) {
            System.out.println("db error during select of person= "+e);
            return;
        }
    }

    @Override
    public void refresh(Connection conn) {
        initialize (conn);
    }

    @Override
    public void save(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            String sqlStmt = "insert into ActorInMedia values ("+personID+","+mediaItemID+",'"+role+"')";
            stmt.executeUpdate(sqlStmt);
        } catch (Exception e) {
            System.out.println("db error during insert of ActorInMedia="+e);
            return;
        }
    }

    @Override
    public void update(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            String sqlStmt = "UPDATE ActorInMedia set role='"+role+"' where personID=" + personID + ", mediaItemID=" + mediaItemID + ")";
            stmt.executeUpdate(sqlStmt);
        } catch (Exception e) {
            System.out.println("db error during update of ActorInMedia="+e);
            return;
        }
    }
}
