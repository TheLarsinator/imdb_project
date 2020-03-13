package moviedb.tables;

import moviedb.ActiveDomainObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User extends ActiveDomainObject {

    String username;
    String email;

    public User(String username, String email){
        this.username = username;
        this.email = email;
    }

    public User(String username) {
        this.username = username;
    }

    @Override
    public void initialize(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select email from User where username='" + username + "'");
            while (rs.next()) {
                this.email =  rs.getString("email");
            }

        } catch (Exception e) {
            System.out.println("db error during select of user= "+e);
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
            String sqlStmt = "insert into User values ('"+username+"','"+email+"')";
            stmt.executeUpdate(sqlStmt);
        } catch (Exception e) {
            System.out.println("db error during insert of User="+e);
            return;
        }
    }

    @Override
    public void update(Connection conn) {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
