package com.charuniverse;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrepareStatementTest {

    @Test
    void testPrepareStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, "admin");
        preparedStatement.setString(2, "admin");

        ResultSet result = preparedStatement.executeQuery();
        if (result.next()) {
            String username = result.getString("username");
            String password = result.getString("password");
            System.out.println(
                    String.join(" ", "Username:", username, "Password:", password)
            );
        } else {
            System.out.println("Gagal login");
        }

        preparedStatement.close();
        connection.close();
    }
}
