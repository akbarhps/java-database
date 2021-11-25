package com.charuniverse;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {

    @Test
    void testTransaction() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";
        for (int i = 0; i < 100; i++) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "test@test.com");
            preparedStatement.setString(2, "test comment" + i);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }

//        connection.commit();
//        connection.rollback();
        connection.close();
    }
}
