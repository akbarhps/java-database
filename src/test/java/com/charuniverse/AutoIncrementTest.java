package com.charuniverse;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class AutoIncrementTest {

    @Test
    void testAutoIncrement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, "test@test.com");
        preparedStatement.setString(2, "test comment");
        preparedStatement.executeUpdate();

        ResultSet result = preparedStatement.getGeneratedKeys();
        if (result.next()) {
            System.out.println("id comment:" + result.getInt(1));
        }

        preparedStatement.close();
        connection.close();
    }
}
