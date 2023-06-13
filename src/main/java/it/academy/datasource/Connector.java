package it.academy.datasource;

import it.academy.entity.Gender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static it.academy.util.DataQuery.EMPTY_STRING;
import static it.academy.util.DataQuery.FIRST_QUERY_INDEX;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

/**
 * @author Katerina
 * @version 1.0
 */
public final class Connector {
    /**
     * Default constructor.
     */
    private Connector() {
    }

    /**
     * Create statement object.
     *
     * @param query      SQL query for statement
     * @param pKey       true when should return generating key,
     *                   false - not return generating key
     * @param connection current connection, which use statement
     * @return fill statement.
     */
    public static PreparedStatement getStatement(final String query,
                                                 final boolean pKey,
                                                 final Connection connection) {

        PreparedStatement statement = null;
        try {
            if (connection != null) {
                statement = pKey ? connection.prepareStatement(query,
                        RETURN_GENERATED_KEYS)
                        : connection.prepareStatement(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    /**
     * Commit change.
     *
     * @param connection current connection
     * @param statement  current statement
     */
    public static void commit(final Connection connection,
                              final PreparedStatement statement) {
        try {
            statement.executeUpdate();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * Return result query in database.
     *
     * @param statement current statement
     * @return result query.
     */
    public static ResultSet execute(final PreparedStatement statement) {
        try {
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Set values in query without primary key.
     *
     * @param values    list values entity
     * @param stmt      current statement
     * @param <TEntity> type entity
     */
    public static <TEntity> void setValues(final List<TEntity> values,
                                           final PreparedStatement stmt) {
        int i = FIRST_QUERY_INDEX;
        for (TEntity value : values) {
            setValueStatement(i, value, stmt);
            i++;
        }
    }

    /**
     * Set values in query with primary key.
     *
     * @param values    list values entity
     * @param idValue   value primary key
     * @param stmt      current statement
     * @param <TEntity> type entity
     */
    public static <TEntity> void setValuesWithPk(final List<TEntity> values,
                                                 final TEntity idValue,
                                                 final PreparedStatement stmt) {
        int i = FIRST_QUERY_INDEX;
        for (TEntity value : values) {
            setValueStatement(i, value, stmt);
            i++;
        }
        setValueStatement(i, idValue, stmt);
    }

    /**
     * Set values in query with primary key.
     *
     * @param value     values primary key
     * @param stmt      current statement
     * @param <TEntity> type entity
     */
    public static <TEntity> void setValuePk(final TEntity value,
                                            final PreparedStatement stmt) {
        setValueStatement(FIRST_QUERY_INDEX, value, stmt);
    }

    /**
     * Set value in statement query.
     *
     * @param index     number value in statement query
     * @param value     value field
     * @param stmt      current statement
     * @param <TEntity> type entity
     */
    private static <TEntity> void setValueStatement(
            final int index,
            final TEntity value,
            final PreparedStatement stmt) {

        try {
            if (value == null) {
                stmt.setString(index, EMPTY_STRING);
            } else if (Integer.class.equals(value.getClass())) {
                stmt.setInt(index, (int) value);
            } else if (Gender.class.equals(value.getClass())) {
                stmt.setString(index, value.toString());
            } else {
                stmt.setString(index, (String) value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return object Entity of result query.
     *
     * @param resultSet result query in database
     * @param clsEntity class Entity
     * @param <TEntity> type Entity
     * @return Entity or null if Entity not find
     */
    public static <TEntity> TEntity getEntity(final ResultSet resultSet,
                                              final Class<TEntity> clsEntity) {
        TEntity entity = null;
        try {
            if (resultSet != null) {
                entity = DataEntity.getEntity(resultSet, clsEntity);
            }
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return entity;
    }

    /**
     * Return list objects Entity of result query.
     *
     * @param resultSet query in database
     * @param clsEntity class Entity
     * @param <TEntity> type Entity
     * @return list Entities
     */
    public static <TEntity> List<TEntity> getListEntity(
            final ResultSet resultSet,
            final Class<TEntity> clsEntity) {
        List<TEntity> listEntity = null;

        try {
            if (resultSet != null) {
                listEntity = DataEntity.getListEntity(resultSet, clsEntity);
            }
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return listEntity;
    }

    /**
     * Set primary key in id field.
     *
     * @param entity     object
     * @param dataEntity metadata object
     * @param statement  current statement
     * @param <TEntity>  type Entity
     * @return generating primary key
     */
    public static <TEntity> Integer setId(final TEntity entity,
                                          final DataEntity<TEntity> dataEntity,
                                          final PreparedStatement statement) {
        Integer id = null;
        try (ResultSet resultSet = statement.getGeneratedKeys()) {
            if (resultSet != null && resultSet.next()) {
                id = resultSet.getInt(FIRST_QUERY_INDEX);
                dataEntity.setId(id, entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}
