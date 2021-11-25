package com.charuniverse;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {

    @Test
    void testStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String sql = "INSERT INTO comments(email, comment) VALUES ('test@test.com', 'test comment')";

        for (int i = 0; i < 1000; i++) {
            statement.addBatch(sql);
        }

        statement.executeBatch();
        statement.close();
        connection.close();
    }

    @Test
    void testPrepareStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < 1000; i++) {
            preparedStatement.clearParameters();
            preparedStatement.setString(1, "test@test.com");
            preparedStatement.setString(2, "test comment" + i);
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
        preparedStatement.close();
        connection.close();
    }
}