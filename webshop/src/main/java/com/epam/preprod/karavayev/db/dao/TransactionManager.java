package com.epam.preprod.karavayev.db.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class TransactionManager {

    private final static Logger LOGGER = Logger.getLogger(TransactionManager.class);
    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void manageTransaction(VoidOperation operation, int transactionLevel) {

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            ConnectionManager.setConnection(connection);
            connection.setTransactionIsolation(transactionLevel);
            connection.setAutoCommit(false);
            operation.execute();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(e);
            rollback(connection);
        } finally {
            close(connection);
            ConnectionManager.removeConnection();
        }
    }

    private void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
