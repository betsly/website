/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.DB;

import at.kaindorf.beans.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SF <htlkaindorf.at>
 */
public class DB_Access {

    /**
     * implement class as singleton
     */
    private static DB_Access theInstance = null;
    private DB_Database db;
    private final String insertUserString = "INSERT INTO user_account (username, password, email) "
            + "VALUES ( ? , ? , ?);";
    private PreparedStatement insertUserPrStat = null;

    public static DB_Access getInstance() throws SQLException {
        if (theInstance == null) {
            theInstance = new DB_Access();
        }
        return theInstance;
    }

    private DB_Access() throws SQLException {
        try {
            db = new DB_Database();
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Database problem - possible cause: DB-Driver not found");
        } catch (SQLException ex) {
            throw new RuntimeException("Database problem - possible cause: " + ex.toString());
        }
    }
    
    public List<User> getUsername() throws SQLException{
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user_account;";
        Statement prep = db.getStatement();
        ResultSet rs = prep.executeQuery(sql);
        while(rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            users.add(new User(email, username, password));
        }
        return users;
    }
    
    public boolean insertUser(User user) throws SQLException{
        if (insertUserPrStat == null) {
            insertUserPrStat = db.getConnection().prepareStatement(insertUserString);
        }
        
        insertUserPrStat.setString(1, user.getUsername());
        insertUserPrStat.setInt(2, user.getPw().hashCode());
        insertUserPrStat.setString(3, user.getEmail());
        
        int numDataSets = insertUserPrStat.executeUpdate();
        return numDataSets > 0;
    }
}
