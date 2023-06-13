package it.academy.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionDBTest {
    @Test
    void isShouldGetConnection() throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        Assertions.assertNotNull(connection);

        connection.close();
    }

}