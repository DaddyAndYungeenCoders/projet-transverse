package com.simulator.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BddService {
    private static final String URL = "jdbc:postgresql://localhost:5432/";
    private static final String DB_NAME = "simulateur";
    private static final String USER = "postgres";
    private static final String PASSWORD = "itssecret";

    public ResultSet Query(String query) {
        try {
            Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                return statement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void Query(String query, Object... args) {
        try {
            Connection connection = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                // Remplacer les paramètres dans la requête avec les valeurs correspondantes
                for (int i = 0; i < args.length; i++) {
                    statement.setObject(i + 1, args[i]);
                }
                // Exécuter la requête
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
