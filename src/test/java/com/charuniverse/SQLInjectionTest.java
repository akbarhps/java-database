package com.charuniverse;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class SQLInjectionTest {

    @Test
    void testSQLInjection() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String username = "' or 1=1; #";
        String password = "admin";

        String query = "select * from admin where username = '" + username + "' and password = '" + password + "'";

        ResultSet result = statement.executeQuery(query);
        if (result.next()) {
            String user = result.getString("username");
            String pass = result.getString("password");

            System.out.println(
                    String.join(", ", user, pass)
            );
        } else {
            System.out.println("Gagal Login");
        }

        result.close();
        statement.close();
        connection.close();
    }
}
