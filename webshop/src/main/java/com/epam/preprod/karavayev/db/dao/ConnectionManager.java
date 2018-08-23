package com.epam.preprod.karavayev.db.dao;

import java.sql.Connection;

public class ConnectionManager {

    private static final ThreadLocal<Connection> connections = new ThreadLocal<>();

    public static void setConnection(Connection connection) {
        connections.set(connection);
    }

    public static Connection getConnection() {
        return connections.get();
    }

    public static void removeConnection() {
        connections.remove();
    }
}
