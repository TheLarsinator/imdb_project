package moviedb.tables;

import com.mysql.cj.protocol.Resultset;
import moviedb.ActiveDomainObject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public class Person extends ActiveDomainObject {

    String name;
    int birthYear;
    Date birthDate;
    String nationality;

    public Person(String name){
        this.name = name;
    }

    public Person(String birthDate, String name, String nationality){
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
            String sql = "select * from Person where name='" + name + "'";
            ResultSet rs = stmt.executeQuery(sql);
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
            String sqlStmt = "insert into Person values ('"+name+"',"+birthYear+",'"+birthDate+"','"+nationality+"')";
            stmt.executeUpdate(sqlStmt);
        } catch (Exception e) {
            System.out.println("db error during insert of Person="+e);
            return;
        }
    }

    @Override
    public void update(Connection conn) {

    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getNationality() {
        return nationality;
    }
}
