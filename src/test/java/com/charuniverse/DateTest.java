package com.charuniverse;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class DateTest {

    @Test
    void testDateInsert() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "INSERT INTO sample_time(sample_date, sample_time, sample_timestamp) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setDate(1, new Date(System.currentTimeMillis()));
        preparedStatement.setTime(2, new Time(System.currentTimeMillis()));
        preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        preparedStatement.executeUpdate();

        ResultSet result = preparedStatement.getGeneratedKeys();
        if (result.next()) {
            System.out.println("id comment:" + result.getInt(1));
        }

        preparedStatement.close();
        connection.close();
    }

    @Test
    void testQueryDate() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();

        String sql = "SELECT * FROM sample_time";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet result = preparedStatement.executeQuery();
        if (result.next()) {
            Time time = result.getTime("sample_time");
            System.out.println("time:" + time);

            Date date = result.getDate("sample_date");
            System.out.println("date:" + date);

            Timestamp timestamp = result.getTimestamp("sample_timestamp");
            System.out.println("timestamp:" + timestamp);
        }

        preparedStatement.close();
        connection.close();
    }
}
