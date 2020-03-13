package moviedb.tables;

import com.mysql.cj.protocol.Resultset;
import moviedb.ActiveDomainObject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public class Person extends ActiveDomainObject {

    private static int NO_ID = -1;

    public int personID;
    int birthYear;
    Date birthDate;
    String nationality;
    String name;

    public Person(String birthDate, String name, String nationality){
        this.personID = NO_ID;
        this.birthDate = Date.valueOf(birthDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.birthDate);
        this.birthYear = cal.get(Calendar.YEAR);
        this.nationality = nationality;
        this.name = name;
    }

    @Override
    public void initialize(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Person where personID=" + personID);
            while (rs.next()) {
                this.name =  rs.getString("name");
                this.nationality =  rs.getString("nationality");
                this.birthYear = rs.getInt("birthYear");
                this.birthDate = rs.getDate("birthDate");
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
            String sqlStmt = "insert into Person values (NULL,"+birthYear+",'"+birthDate+"','"+nationality+"','" + name+"')";
            stmt.executeUpdate(sqlStmt, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys();
            if(keys.next())
                personID = keys.getInt(1);
        } catch (Exception e) {
            System.out.println("db error during insert of Person="+e);
            return;
        }
    }

    @Override
    public void update(Connection conn) {

    }
}
