package it.academy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static it.academy.util.DataDB.CONNECTION_DRIVER;
import static it.academy.util.DataDB.CONNECTION_NAME;
import static it.academy.util.DataDB.CONNECTION_PASSWORD;
import static it.academy.util.DataDB.CONNECTION_URL;
import static it.academy.util.DataDB.CONNECTION_USER;

/**
 * @author Katerina
 * @version 1.0
 */
public final class ConnectionDB {
    /**
     * Default constructor.
     */
    private ConnectionDB() {
    }

    /**
     * Properties connection.
     */
    private static final ResourceBundle PROPERTIES =
            ResourceBundle.getBundle(CONNECTION_NAME);

    /**
     * Name driver properties.
     */
    public static final String DRIVER = PROPERTIES.getString(CONNECTION_DRIVER);

    /**
     * Name URL properties.
     */
    public static final String URL = PROPERTIES.getString(CONNECTION_URL);

    /**
     * Name user properties.
     */
    public static final String USER = PROPERTIES.getString(CONNECTION_USER);

    /**
     * Name password properties.
     */
    public static final String PASSWORD = PROPERTIES.
            getString(CONNECTION_PASSWORD);

    /**
     * Create connection object.
     *
     * @return Connection object
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
