package com.charuniverse;

import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

    @BeforeAll
    public static void setUp() {
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/playground";
        String username = "root";
        String password = "root";

        // try with result tidak wajib di close
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Connection successful");
            System.out.println("Harusnya otomatically di close");

//            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
//            connection.close();
            // membuka koneksi didalam try wajib di close
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
