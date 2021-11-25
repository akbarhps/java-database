package com.charuniverse;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolTest {

    @Test
    void testHikariCP() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/playground");
        config.setUsername("root");
        config.setPassword("root");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60_000);

        try {
            HikariDataSource dataSource = new HikariDataSource(config);
            Connection connection = dataSource.getConnection();
            connection.close(); // mengembalikan koneksi ke dalam pool
            dataSource.close(); // menutup semua koneksi yang ada di dalam pool
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testConnectViaConnectionUtil() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
    }

}
