package moviedb.tables;

import moviedb.ActiveDomainObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProducerInMedia extends ActiveDomainObject {

    int personID;
    int mediaItemID;
    public String role;

    public ProducerInMedia(int personID, int mediaItemID, String role){
        this.personID = personID;
        this.mediaItemID = mediaItemID;
        this.role = role;
    }

    @Override
    public void initialize(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ProducerInMedia where personID=" + personID + " and mediaItemID =" + mediaItemID);
            while (rs.next()) {
                this.role =  rs.getString("role");
            }

        } catch (Exception e) {
            System.out.println("db error during select of ProducerInMedia= "+e);
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
            String sqlStmt = "insert into ProducerInMedia values ("+personID+","+mediaItemID+",'"+role+"')";
            stmt.executeUpdate(sqlStmt);
        } catch (Exception e) {
            System.out.println("db error during insert of ProducerInMedia="+e);
            return;
        }
    }

    @Override
    public void update(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            String sqlStmt = "UPDATE ProducerInMedia set role='"+role+"' where personID=" + personID + ", mediaItemID=" + mediaItemID + ")";
            stmt.executeUpdate(sqlStmt);
        } catch (Exception e) {
            System.out.println("db error during update of ProducerInMedia="+e);
            return;
        }
    }
}
