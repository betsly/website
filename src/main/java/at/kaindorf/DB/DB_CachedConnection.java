/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.kaindorf.DB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

/**
 * class to manage statement pool for database access
 *
 * @author SF <htlkaindorf.at>
 */
public class DB_CachedConnection {

    private Queue<Statement> statementQueue = new LinkedList<>();                   // Queue: a, b, c
    private Connection connection;

    public DB_CachedConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * returns a valid statement to the database
     *
     * @return
     * @throws SQLException
     */
    public Statement getStatement() throws SQLException {
        if (connection == null) {
            throw new RuntimeException("not connected to DB");
        }
        if (!statementQueue.isEmpty()) {
            return statementQueue.poll();
        }
        return connection.createStatement();
    }

    /**
     * returns a statement to the queue
     *
     * @param statement
     */
    public void releaseStatement(Statement statement) {
        if (connection == null) {
            throw new RuntimeException("not connected to DB");
        }
        statementQueue.offer(statement);
    }

    private Exception RuntimeException(String not_connected_to_DB) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
