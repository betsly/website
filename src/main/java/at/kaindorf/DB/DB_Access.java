/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.DB;

import at.kaindorf.beans.Group;
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

    private PreparedStatement insertUserPrStat = null;
    private final String insertUserString = "INSERT INTO user_account (username, password, email) "
            + "VALUES ( ? , ? , ?);";

    private PreparedStatement insertGroupPrStat = null;
    private final String insertGroupString = "INSERT INTO public.\"group\" (description, name) "
            + "VALUES ( ? , ?);";

    private PreparedStatement insertGroupOwnerPrStat = null;
    private final String insertGroupOwnerString = "INSERT INTO group_owner (owner_id, group_id) "
            + "VALUES ( ? , ?);";

    private PreparedStatement insertGroupUserPrStat = null;
    private final String insertGroupUserString = "INSERT INTO group_user (user_id, group_id) "
            + "VALUES ( ? , ?);";

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

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user_account;";
        Statement prep = db.getStatement();
        ResultSet rs = prep.executeQuery(sql);
        while (rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            users.add(new User(email, username, password));
        }
        return users;
    }

    public boolean insertUser(User user) throws SQLException {
        if (insertUserPrStat == null) {
            insertUserPrStat = db.getConnection().prepareStatement(insertUserString);
        }
        insertUserPrStat.setString(1, user.getUsername());
        insertUserPrStat.setInt(2, user.getPw().hashCode());
        insertUserPrStat.setString(3, user.getEmail());
        int numDataSets = insertUserPrStat.executeUpdate();
        return numDataSets > 0;
    }

    public int getPasswordByEmail(String email) throws SQLException {
        String password = "";
        String sql = "SELECT password FROM user_account WHERE email = \'" + email + "\';";
        Statement prep = db.getStatement();
        ResultSet rs = prep.executeQuery(sql);
        while (rs.next()) {
            password = rs.getString("password");
        }
        return password != "" ? Integer.parseInt(password) : -1;
    }

    public List<Group> getJoinedGroups(String email) throws SQLException {
        List<Group> joinedGroups = new ArrayList<>();
        String sql = "SELECT public.\"group\".group_id, name, description\n"
                + "FROM public.\"group\" INNER JOIN public.\"group_user\" ON public.\"group\".group_id = public.\"group_user\".group_id\n"
                + "WHERE user_id = 'test@test.test';";
        Statement prep = db.getStatement();
        ResultSet rs = prep.executeQuery(sql);
        while (rs.next()) {
            int group_id = rs.getInt("group_id");
            String group_name = rs.getString("name");
            String description = rs.getString("description");
            joinedGroups.add(new Group(group_id, group_name, description));
        }
        return joinedGroups;
    }

    public boolean createGroup(String name, String description, String userID) throws SQLException {
        //create group entry
        if (insertGroupPrStat == null) {
            insertGroupPrStat = db.getConnection().prepareStatement(insertGroupString);
        }
        insertGroupPrStat.setString(1, description);
        insertGroupPrStat.setString(2, name);
        int numDataSets = insertGroupPrStat.executeUpdate();
        if (numDataSets < 0) {
            return false;
        }

        //create group_owner entry
        String sql = "SELECT group_id FROM public.\"group\" WHERE name = \'" + name + "\';";
        Statement prep = db.getStatement();
        ResultSet rs = prep.executeQuery(sql);
        int group_id = 1;
        while (rs.next()) {
            group_id = Integer.parseInt(rs.getString("group_id"));
        }
        if (insertGroupOwnerPrStat == null) {
            insertGroupOwnerPrStat = db.getConnection().prepareStatement(insertGroupOwnerString);
        }
        insertGroupOwnerPrStat.setString(1, userID);
        insertGroupOwnerPrStat.setInt(2, group_id);
        numDataSets = insertGroupOwnerPrStat.executeUpdate();
        if (numDataSets < 0) {
            return false;
        }

        //create group_user entry 
        if (insertGroupUserPrStat == null) {
            insertGroupUserPrStat = db.getConnection().prepareStatement(insertGroupUserString);
        }
        insertGroupUserPrStat.setString(1, userID);
        insertGroupUserPrStat.setInt(2, group_id);
        numDataSets = insertGroupUserPrStat.executeUpdate();
        return numDataSets > 0;
    }

    public boolean joinGroup(String name, String userID) throws SQLException {
        String sql = "SELECT group_id FROM public.\"group\" WHERE name = \'" + name + "\';";
        Statement prep = db.getStatement();
        ResultSet rs = prep.executeQuery(sql);
        int group_id = 1;
        while (rs.next()) {
            group_id = Integer.parseInt(rs.getString("group_id"));
        }
        if (insertGroupUserPrStat == null) {
            insertGroupUserPrStat = db.getConnection().prepareStatement(insertGroupUserString);
        }
        insertGroupUserPrStat.setString(1, userID);
        insertGroupUserPrStat.setInt(2, group_id);
        int numDataSets = insertGroupUserPrStat.executeUpdate();
        return numDataSets > 0;
    }

//    public boolean createGroup(String email){
//        int numDataSets = insertUserPrStat.executeUpdate();
//        return numDataSets > 0;
//    }
//    
//    public boolean joinGroup(String email){
//        int numDataSets = insertUserPrStat.executeUpdate();
//        return numDataSets > 0;
//    }
}
