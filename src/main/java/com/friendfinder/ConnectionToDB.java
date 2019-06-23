package com.friendfinder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionToDB {

    public Connection connection;

    public Connection getConnection() {

        String dbName = "FriendFinder";
        String username = "postgres";
        String password = "bujhmgtnhjd2002";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, username, password);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }
}
