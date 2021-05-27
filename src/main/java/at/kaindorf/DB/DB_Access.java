/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.DB;

import at.kaindorf.beans.BetGroupPhase;
import at.kaindorf.beans.BetKoPhase;
import at.kaindorf.beans.Country;
import at.kaindorf.beans.Group;
import at.kaindorf.beans.GroupUser;
import at.kaindorf.beans.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final String insertGroupString = "INSERT INTO public.\"group\" (description, name, password) "
            + "VALUES ( ? , ?, ?);";

    private PreparedStatement insertGroupOwnerPrStat = null;
    private final String insertGroupOwnerString = "INSERT INTO group_owner (owner_id, group_id) "
            + "VALUES ( ? , ?);";

    private PreparedStatement insertGroupUserPrStat = null;
    private final String insertGroupUserString = "INSERT INTO group_user (user_id, group_id, points) "
            + "VALUES ( ? , ?, 0);";

    private PreparedStatement insertCountriesPrStat = null;
    private final String insertCountrieString = "INSERT INTO country (country) "
            + "VALUES ( ? );";

    private PreparedStatement getPasswordByEmailPrStat;
    private final String getPasswordByEmailString = "SELECT password FROM user_account WHERE email = ?;";

    private PreparedStatement getJoinedGroupsPrStat;
    private String getJoinedGroupsString = "SELECT public.\"group\".group_id, name, description, password, public.\"group_owner\".owner_id, public.\"group_user\".points\n"
            + "FROM public.\"group\" INNER JOIN public.\"group_user\" ON public.\"group\".group_id = public.\"group_user\".group_id\n"
            + "INNER JOIN public.\"group_owner\" ON public.\"group\".group_id = public.\"group_owner\".group_id\n"
            + "WHERE user_id = ?;";

    private PreparedStatement createGroupPrStat;
    private String createGroupString = "SELECT group_id FROM public.\"group\" WHERE name = ?;";

    private PreparedStatement joinGroupPrStat;
    private String joinGroupString = "SELECT group_id FROM public.\"group\" WHERE name = ?;";

    private PreparedStatement getPasswordByGroupNamePrStat;
    private final String getPasswordByGroupNameString = "SELECT password FROM public.\"group\" WHERE name = ?;";

    private PreparedStatement createBetGroupPhasePrStat;
    private final String createBetGroupPhaseString = "INSERT INTO bet_group_phase (name, group_id, country1, country2, country3, country4)"
            + "VALUES ( ?, ?, ?, ?, ?, ? );";

    private PreparedStatement createBetKOPhasePrStat;
    private final String createBetKOPhaseString = "INSERT INTO bet_ko_phase (name, group_id, country1, country2)"
            + "VALUES ( ?, ?, ?, ? );";

    private PreparedStatement getBetGroupPhasePrStat;
    private final String getBetGroupPhaseString = "SELECT *\n"
            + "FROM bet_group_phase\n"
            + "WHERE group_id = ?";

    private PreparedStatement getBetKOPhasePrStat;
    private final String getBetKOPhaseString = "SELECT *\n"
            + "FROM bet_ko_phase\n"
            + "WHERE group_id = ?";

    private PreparedStatement getCountryByIDPrStat;
    private final String getCountryByIDString = "SELECT country\n"
            + "FROM country\n"
            + "WHERE country_id = ?";

    private PreparedStatement makeBetKOPhasePrStat;
    private final String makeBetKOPhaseString = "INSERT INTO bet_ko_phase_user (bet_id, user_id, tip, score)"
            + "VALUES ( ?, ?, ?, ? );";

    private PreparedStatement makeBetGroupPhasePrStat;
    private final String makeBetGroupPhaseString = "INSERT INTO bet_group_phase_user (bet_id, user_id, country1, country2, country3, country4)"
            + "VALUES ( ?, ?, ?, ?, ?, ? );";

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
        if (getPasswordByEmailPrStat == null) {
            getPasswordByEmailPrStat = db.getConnection().prepareStatement(getPasswordByEmailString);
        }
        getPasswordByEmailPrStat.setString(1, email);
        ResultSet rs = getPasswordByEmailPrStat.executeQuery();
        while (rs.next()) {
            password = rs.getString("password");
        }
        return !password.equals("") ? Integer.parseInt(password) : -1;
    }

    public List<Group> getJoinedGroups(String email) throws SQLException {
        List<Group> joinedGroups = new ArrayList<>();
        if (getJoinedGroupsPrStat == null) {
            getJoinedGroupsPrStat = db.getConnection().prepareStatement(getJoinedGroupsString);
        }
        getJoinedGroupsPrStat.setString(1, email);
        ResultSet rs = getJoinedGroupsPrStat.executeQuery();
        while (rs.next()) {
            int group_id = rs.getInt("group_id");
            String group_name = rs.getString("name");
            String description = rs.getString("description");
            String password = rs.getString("password");
            String ownerId = rs.getString("owner_id");
            int points = rs.getInt("points");
            joinedGroups.add(new Group(group_id, group_name, description, password, ownerId, points));
        }
        return joinedGroups;
    }

    public boolean createGroup(String name, String description, String password, String userID) throws SQLException {
        //create group entry
        if (insertGroupPrStat == null) {
            insertGroupPrStat = db.getConnection().prepareStatement(insertGroupString);
        }
        insertGroupPrStat.setString(1, description);
        insertGroupPrStat.setString(2, name);
        insertGroupPrStat.setInt(3, password.hashCode());
        int numDataSets = insertGroupPrStat.executeUpdate();
        if (numDataSets < 0) {
            return false;
        }

        //create group_owner entry
        if (createGroupPrStat == null) {
            createGroupPrStat = db.getConnection().prepareStatement(createGroupString);
        }
        createGroupPrStat.setString(1, name);
        ResultSet rs = createGroupPrStat.executeQuery();
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
        if (joinGroupPrStat == null) {
            joinGroupPrStat = db.getConnection().prepareStatement(joinGroupString);
        }
        joinGroupPrStat.setString(1, name);
        ResultSet rs = joinGroupPrStat.executeQuery();
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

    public int getPasswordByGroupName(String groupName) throws SQLException {
        String password = "";
        if (getPasswordByGroupNamePrStat == null) {
            getPasswordByGroupNamePrStat = db.getConnection().prepareStatement(getPasswordByGroupNameString);
        }
        getPasswordByGroupNamePrStat.setString(1, groupName);
        ResultSet rs = getPasswordByGroupNamePrStat.executeQuery();
        while (rs.next()) {
            password = rs.getString("password");
        }
        return password != "" ? Integer.parseInt(password) : -1;
    }

    public boolean writeCountries(List<String> countries) throws SQLException {
        for (String country : countries) {
            if (insertCountriesPrStat == null) {
                insertCountriesPrStat = db.getConnection().prepareStatement(insertCountrieString);
            }
            insertCountriesPrStat.setString(1, country);
            int numDataSets = insertCountriesPrStat.executeUpdate();
            if (numDataSets < 0) {
                return false;
            }
        }
        return true;
    }

    public List<Country> getCountries() throws SQLException {
        List<Country> counries = new ArrayList<>();
        String sql = "SELECT * FROM country;";
        Statement prep = db.getStatement();
        ResultSet rs = prep.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("country_id");
            String name = rs.getString("country");
            counries.add(new Country(id, name));
        }
        return counries;
    }

    public boolean createBetGroupPhase(Country[] countries, int groupId, String name) throws SQLException {
        if (createBetGroupPhasePrStat == null) {
            createBetGroupPhasePrStat = db.getConnection().prepareStatement(createBetGroupPhaseString);
        }
        createBetGroupPhasePrStat.setString(1, name);
        createBetGroupPhasePrStat.setInt(2, groupId);
        createBetGroupPhasePrStat.setInt(3, countries[0].getId());
        createBetGroupPhasePrStat.setInt(4, countries[1].getId());
        createBetGroupPhasePrStat.setInt(5, countries[2].getId());
        createBetGroupPhasePrStat.setInt(6, countries[3].getId());
        int numDataSets = createBetGroupPhasePrStat.executeUpdate();
        return numDataSets > 0;
    }

    public boolean createBetKOPhase(Country[] countries, int groupId, String name) throws SQLException {
        if (createBetKOPhasePrStat == null) {
            createBetKOPhasePrStat = db.getConnection().prepareStatement(createBetKOPhaseString);
        }
        createBetKOPhasePrStat.setString(1, name);
        createBetKOPhasePrStat.setInt(2, groupId);
        createBetKOPhasePrStat.setInt(3, countries[0].getId());
        createBetKOPhasePrStat.setInt(4, countries[1].getId());
        int numDataSets = createBetKOPhasePrStat.executeUpdate();
        return numDataSets > 0;
    }

    public List<BetGroupPhase> getGroupPhaseBetsByGroup(int groupId) throws SQLException {
        List<BetGroupPhase> bets = new ArrayList<>();
        if (getBetGroupPhasePrStat == null) {
            getBetGroupPhasePrStat = db.getConnection().prepareStatement(getBetGroupPhaseString);
        }
        getBetGroupPhasePrStat.setInt(1, groupId);
        ResultSet rs = getBetGroupPhasePrStat.executeQuery();
        while (rs.next()) {
            int betId = rs.getInt("bet_id");
            String name = rs.getString("name");
            boolean closed = rs.getBoolean("closed");
            Country country1 = getCountryByID(rs.getInt("country1"));
            Country country2 = getCountryByID(rs.getInt("country2"));
            Country country3 = getCountryByID(rs.getInt("country3"));
            Country country4 = getCountryByID(rs.getInt("country4"));
            bets.add(new BetGroupPhase(betId, groupId, name, country1, country2, country3, country4, closed));
        }
        return bets;
    }

    public List<BetKoPhase> getKOPhaseBetsByGroup(int groupId) throws SQLException {
        List<BetKoPhase> bets = new ArrayList<>();
        if (getBetKOPhasePrStat == null) {
            getBetKOPhasePrStat = db.getConnection().prepareStatement(getBetKOPhaseString);
        }
        getBetKOPhasePrStat.setInt(1, groupId);
        ResultSet rs = getBetKOPhasePrStat.executeQuery();
        while (rs.next()) {
            int betId = rs.getInt("bet_id");
            String name = rs.getString("name");
            boolean closed = rs.getBoolean("closed");
            Country country1 = getCountryByID(rs.getInt("country1"));
            Country country2 = getCountryByID(rs.getInt("country2"));
            bets.add(new BetKoPhase(betId, groupId, name, country1, country2, closed));
        }
        return bets;
    }

    public Country getCountryByID(int id) throws SQLException {
        Country country = null;
        if (getCountryByIDPrStat == null) {
            getCountryByIDPrStat = db.getConnection().prepareStatement(getCountryByIDString);
        }
        getCountryByIDPrStat.setInt(1, id);
        ResultSet rs = getCountryByIDPrStat.executeQuery();
        while (rs.next()) {
            String name = rs.getString("country");
            country = new Country(id, name);
        }
        return country;
    }

    public boolean makeBetKOPhase(int betId, String userId, int tip, String score) throws SQLException {
        if (makeBetKOPhasePrStat == null) {
            makeBetKOPhasePrStat = db.getConnection().prepareStatement(makeBetKOPhaseString);
        }
        makeBetKOPhasePrStat.setInt(1, betId);
        makeBetKOPhasePrStat.setString(2, userId);
        makeBetKOPhasePrStat.setInt(3, tip);
        makeBetKOPhasePrStat.setString(4, score);
        int numDataSets = makeBetKOPhasePrStat.executeUpdate();
        return numDataSets > 0;
    }

    public boolean makeBetGroupPhase(int betId, String userId, int c1, int c2, int c3, int c4) throws SQLException {
        if (makeBetGroupPhasePrStat == null) {
            makeBetGroupPhasePrStat = db.getConnection().prepareStatement(makeBetGroupPhaseString);
        }
        makeBetGroupPhasePrStat.setInt(1, betId);
        makeBetGroupPhasePrStat.setString(2, userId);
        makeBetGroupPhasePrStat.setInt(3, c1);
        makeBetGroupPhasePrStat.setInt(4, c2);
        makeBetGroupPhasePrStat.setInt(5, c3);
        makeBetGroupPhasePrStat.setInt(6, c4);
        int numDataSets = makeBetGroupPhasePrStat.executeUpdate();
        return numDataSets > 0;
    }

    public boolean closeBetKOPhase(int betID) throws SQLException {
        String sql = "UPDATE public.bet_ko_phase\n"
                + "SET closed=TRUE\n"
                + "WHERE bet_id = " + betID + ";";
        Statement prep = db.getStatement();
        return prep.executeUpdate(sql) > 0;
    }
    
    public boolean closeBetGroupPhase(int betID) throws SQLException {
        String sql = "UPDATE public.bet_group_phase\n"
                + "SET closed=TRUE\n"
                + "WHERE bet_id = " + betID + ";";
        Statement prep = db.getStatement();
        return prep.executeUpdate(sql) > 0;
    }

    public boolean distributePointsKO(String winnerID, String[] endScore, int betID, int groupID) throws SQLException {
        String sql = "SELECT *\n"
                + "FROM bet_ko_phase_user\n"
                + "WHERE bet_id = " + betID + ";";
        Statement prep = db.getStatement();
        ResultSet rs = prep.executeQuery(sql);
        int points = 0;
        int numDataSets = 0;
        while (rs.next()) {
            points += rs.getString("tip").equals(winnerID) ? 3 : 0;
            String[] score = rs.getString("score").split(":");
            points += score[0].equals(endScore[0]) ? 2 : 0;
            points += score[1].equals(endScore[1]) ? 2 : 0;
            String sqlString = "UPDATE public.group_user\n"
                    + "SET points = points + " + points +"\n"
                    + "WHERE user_id = '" + rs.getString("user_id") + "' AND group_id = " + groupID + ";";
            Statement prepStatement = db.getStatement();
            numDataSets = prepStatement.executeUpdate(sqlString);
            points = 0;
        }
        return numDataSets > 0;
    }
    
    public boolean distributePointsGroup(String first, String second, String third, String fourth, int betID, int groupID) throws SQLException {
        String sql = "SELECT *\n"
                + "FROM bet_group_phase_user\n"
                + "WHERE bet_id = " + betID + ";";
        Statement prep = db.getStatement();
        ResultSet rs = prep.executeQuery(sql);
        int points = 0;
        int numDataSets = 0;
        while (rs.next()) {
            points += rs.getString("country1").equals(first) ? 2 : 0;
            points += rs.getString("country2").equals(second) ? 2 : 0;
            points += rs.getString("country3").equals(third) ? 2 : 0;
            points += rs.getString("country4").equals(fourth) ? 2 : 0;
            String sqlString = "UPDATE public.group_user\n"
                    + "SET points = points + " + points +"\n"
                    + "WHERE user_id = '" + rs.getString("user_id") + "' AND group_id = " + groupID + ";";
            Statement prepStatement = db.getStatement();
            numDataSets = prepStatement.executeUpdate(sqlString);
            points = 0;
        }
        return numDataSets > 0;
    }
    
    public List<GroupUser> getRanks() throws SQLException{
        List<GroupUser> groupUList = new ArrayList<>();
        String sql = "SELECT * FROM ;";
        Statement prep = db.getStatement();
        ResultSet rs = prep.executeQuery(sql);
        while (rs.next()) {
            int points = rs.getInt("points");
            String userID = rs.getString("user_id");
            int groupID = rs.getInt("group_id");
            groupUList.add(new GroupUser(groupID, userID, points));
        }
        return groupUList;
    }
}
