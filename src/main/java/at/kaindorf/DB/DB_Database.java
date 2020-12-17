/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author SF <htlkaindorf.at>
 */
public class DB_Database {

    private String db_url = "jdbc:postgresql://192.168.0.18:5432/betsly";
    private String db_driver = "org.postgresql.Driver";
    private String db_username = "postgres";
    private String db_password = "";
    private Connection connection;
    private DB_CachedConnection cachedConnection;

    public DB_Database() throws ClassNotFoundException, SQLException {
//      loadProperties();
        Class.forName(db_driver);
        connect();
    }

    /**
     * establish connection to database
     *
     * @throws SQLException
     */
    public void connect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = DriverManager.getConnection(db_url, db_username, db_password);
        cachedConnection = new DB_CachedConnection(connection);
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
            cachedConnection = null;
        }
    }

//    public void loadProperties() {
//        db_url = DB_Properties.getPropertyValue("url");
//        db_driver = DB_Properties.getPropertyValue("driver");
//        db_username = DB_Properties.getPropertyValue("username");
//        db_password = DB_Properties.getPropertyValue("password");
//    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() throws SQLException {
        if (connection == null || cachedConnection == null) {
            throw new RuntimeException("database connection error");
        }
        return cachedConnection.getStatement();
    }

    public void releaseStatement(Statement statement) {
        if (connection == null || cachedConnection == null) {
            throw new RuntimeException("database connection error");
        }
        cachedConnection.releaseStatement(statement);
    }
}
