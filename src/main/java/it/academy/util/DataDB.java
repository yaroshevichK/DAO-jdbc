package it.academy.util;

import it.academy.anno.Column;
import it.academy.anno.Id;
import it.academy.anno.Table;

/**
 * @author Katerina
 * @version 1.0
 */
public final class DataDB {
    /**
     * Default constructor.
     */
    private DataDB() {
    }

    /**
     * Name table person in database.
     */
    public static final String DB_TABLE_PERSON = "person";

    /**
     * Name primary key field in table person in database.
     */
    public static final String DB_COLUMN_ID = "id";

    /**
     * Name field name in table person in database.
     */
    public static final String DB_COLUMN_NAME = "name";

    /**
     * Name field surname in table person in database.
     */
    public static final String DB_COLUMN_SURNAME = "surname";

    /**
     * Name field gender in table person in database.
     */
    public static final String DB_COLUMN_GENDER = "gender";

    /**
     * Name file with properties.
     */
    public static final String CONNECTION_NAME = "database";

    /**
     * Name property driver with properties file.
     */
    public static final String CONNECTION_DRIVER = "driver";

    /**
     * Name property url with properties file.
     */
    public static final String CONNECTION_URL = "url";

    /**
     * Name property user with properties file.
     */
    public static final String CONNECTION_USER = "user";

    /**
     * Name property password with properties file.
     */
    public static final String CONNECTION_PASSWORD = "password";

    /**
     * Annotation Table.
     */
    public static final Class<Table> TABLE = Table.class;

    /**
     * Annotation Column.
     */
    public static final Class<Column> COLUMN = Column.class;

    /**
     * Annotation id.
     */
    public static final Class<Id> ID = Id.class;
}
