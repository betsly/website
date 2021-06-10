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

    //jdbc:postgresql://<host>:<port>/<dbname>
    private String db_url = "jdbc:postgresql://ec2-79-125-30-28.eu-west-1.compute.amazonaws.com:5432/de8pqps1tt6h5";
    private String db_driver = "org.postgresql.Driver";
    private String db_username = "hcninbdmztqqpn";
    private String db_password = "a709002e51affe5c8a5b1998b5d56412985704da837dc66ba4c1b69316e1808f";
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
