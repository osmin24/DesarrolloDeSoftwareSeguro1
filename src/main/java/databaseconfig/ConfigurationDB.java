/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databaseconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP 255-G9
 */
public class ConfigurationDB {
    private static final String USER="postgres";
    private static final String PASSWORD="admin";
    private static final String URL="jdbc:postgresql://localhost:5432/funcionarios";

    /**
     *
     * @return
     * @throws java.sql.SQLException
     */
    public static Connection getConnection() throws SQLException {
       return (Connection)DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
