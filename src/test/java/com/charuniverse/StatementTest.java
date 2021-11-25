package com.charuniverse;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementTest {

    @Test
    void testCreateStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        statement.close();
        connection.close();
    }

    @Test
    void testExecuteUpdate() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String query = """
                INSERT INTO customers(id, name, email)
                VALUES ('test', 'test', 'test@test.com')
                  """;

        int update = statement.executeUpdate(query);
        System.out.println(update);

        statement.close();
        connection.close();
    }

    @Test
    void testExecuteDelete() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String query = """
                DELETE FROM customers;
                """;

        int update = statement.executeUpdate(query);
        System.out.println(update);

        statement.close();
        connection.close();
    }

    @Test
    void testExecuteQuery() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String query = """
                SELECT * FROM customers;
                """;

        ResultSet result = statement.executeQuery(query);

        while (result.next()) {
            String id = result.getString("id");
            String name = result.getString("name");
            String email = result.getString("email");

            System.out.println(
                    String.join(", ", id, name, email)
            );
        }

        result.close();
        statement.close();
        connection.close();
    }
}
