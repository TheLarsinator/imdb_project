package moviedb.tables;

import moviedb.ActiveDomainObject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public class Company extends ActiveDomainObject {

    String companyName;
    String URL;
    String address;
    String country;

    public Company(String companyName){
        this.companyName = companyName;
    }

    @Override
    public void initialize(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            String sql = "select * from Company where companyName='" + companyName + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                this.country =  rs.getString("country");
            }

        } catch (Exception e) {
            System.out.println("db error during select of company= "+e);
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
            String sqlStmt = "insert into Company values ('"+companyName+"',NULL,NULL,'"+country+"')";
            stmt.executeUpdate(sqlStmt);
        } catch (Exception e) {
            System.out.println("db error during insert of Company="+e);
            return;
        }
    }

    @Override
    public void update(Connection conn) {

    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
