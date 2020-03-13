package moviedb.tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Soundtrack extends ActiveDomainObject {
    int soundtrackID;
    String title;

    public Soundtrack(String title) {
        this.soundtrackID = -1;
        this.title = title;
    }

    @Override
    public void initialize(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Soundtrack where soundtrackID=" + soundtrackID);
            while (rs.next()) {
                this.title =  rs.getString("title");
            }

        } catch (Exception e) {
            System.out.println("db error during select of Soundtrack= "+e);
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
            String sqlStmt = "insert into Soundtrack values (NULL, '"+ title +"')";
            stmt.executeUpdate(sqlStmt, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys();
            if(keys.next())
                soundtrackID = keys.getInt(1);
        } catch (Exception e) {
            System.out.println("db error during insert of Movie="+e);
            return;
        }
    }

    @Override
    public void update(Connection conn) {

    }
}
