package moviedb.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MusicProducer extends ActiveDomainObject {

    String name;
    int soundtrackID;
    public String role;

    public MusicProducer(String name, int soundtrackID, String role){
        this.name = name;
        this.soundtrackID = soundtrackID;
        this.role = role;
    }

    @Override
    public void initialize(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from ProducerInMedia where name='" + name + "' and soundtrackID =" + soundtrackID);
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
            String sqlStmt = "insert into ProducerInMedia values ('"+name+"',"+soundtrackID+",'"+role+"')";
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
            String sqlStmt = "UPDATE ProducerInMedia set role='"+role+"' where name='" + name + "', soundtrackID=" + soundtrackID + ")";
            stmt.executeUpdate(sqlStmt);
        } catch (Exception e) {
            System.out.println("db error during update of ProducerInMedia="+e);
            return;
        }
    }
}
