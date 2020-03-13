package moviedb.tables;

import moviedb.ActiveDomainObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProducerInMedia extends ActiveDomainObject {

    String name;
    int mediaItemID;
    public String role;

    public ProducerInMedia(String name, int mediaItemID, String role){
        this.name = name;
        this.mediaItemID = mediaItemID;
        this.role = role;
    }

    @Override
    public void initialize(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ProducerInMedia where name=" + name + " and mediaItemID =" + mediaItemID);
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
            String sqlStmt = "insert into ProducerInMedia values ('"+name+"',"+mediaItemID+",'"+role+"')";
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
            String sqlStmt = "UPDATE ProducerInMedia set role='"+role+"' where name='" + name + "', mediaItemID=" + mediaItemID + ")";
            stmt.executeUpdate(sqlStmt);
        } catch (Exception e) {
            System.out.println("db error during update of ProducerInMedia="+e);
            return;
        }
    }
}
