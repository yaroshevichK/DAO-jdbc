package it.academy.util;

/**
 * @author Katerina
 * @version 1.0
 */
public final class DataQuery {
    /**
     * Default constructor.
     */
    private DataQuery() {
    }

    /**
     * Empty string.
     */
    public static final String EMPTY_STRING = "";

    /**
     * Pattern insert query.
     */
    public static final String INSERT_QUERY =
            "INSERT INTO %s (%s) VALUES (%s);";

    /**
     * Pattern update query.
     */
    public static final String UPDATE_QUERY = "UPDATE %s SET %s WHERE %s = ?;";

    /**
     * Pattern delete query.
     */
    public static final String DELETE_QUERY = "DELETE FROM %s WHERE %s = ?;";

    /**
     * Pattern delete all query.
     */
    public static final String DELETE_ALL_QUERY = "DELETE FROM %s;";

    /**
     * Pattern select query.
     */
    public static final String SELECT_QUERY = "SELECT * FROM %s WHERE %s = ?;";

    /**
     * Pattern select all query.
     */
    public static final String SELECT_ALL_QUERY = "SELECT * FROM %s;";

    /**
     * Character for values in statement query.
     */
    public static final String CHAR_VALUE = "?";

    /**
     * Character for delimiter values in statement query.
     */
    public static final String DELIMITER_COMMA = ",";

    /**
     * Character for delimiter values in update statement query.
     */
    public static final String DELIMITER_UPD = " = ?,";

    /**
     * Character for delimiter last value in update statement query.
     */
    public static final String DELIM_LAST_UPD = " = ?";

    /**
     * Return generating primary key.
     */
    public static final boolean RETURN_P_KEY = true;

    /**
     * Not return generating primary key.
     */
    public static final boolean WITHOUT_P_KEY = false;

    /**
     * First index in statement query.
     */
    public static final int FIRST_QUERY_INDEX = 1;
}
