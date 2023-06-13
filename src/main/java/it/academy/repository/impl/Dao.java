package it.academy.repository.impl;

import it.academy.datasource.Connector;
import it.academy.datasource.DataEntity;
import it.academy.datasource.QueryGeneration;
import it.academy.repository.IDao;
import it.academy.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import static it.academy.util.DataQuery.RETURN_P_KEY;
import static it.academy.util.DataQuery.WITHOUT_P_KEY;

/**
 * @param <TEntity> type Entity
 * @author Katerina
 * @version 1.0
 */
public class Dao<TEntity> implements IDao<TEntity> {
    /**
     * Current connection.
     */
    private final Connection connection;
    /**
     * Current statement.
     */
    private PreparedStatement statement;
    /**
     * Data entity (fields and value).
     */
    private final DataEntity<TEntity> dataEntity = new DataEntity<>();

    /**
     * Default constructor.
     */
    public Dao() {
        connection = ConnectionDB.getConnection();
    }

    /**
     * Create record in database.
     *
     * @param entity object to save
     * @return value primary key
     */
    @Override
    public Integer save(final TEntity entity) {
        dataEntity.getDataEntity(entity);

        statement = Connector.getStatement(QueryGeneration.
                        getInsertQuery(dataEntity),
                RETURN_P_KEY, connection);
        Connector.setValues(dataEntity.getValues(), statement);
        Connector.commit(connection, statement);

        return Connector.setId(entity, dataEntity, statement);
    }

    /**
     * Update record in database.
     *
     * @param entity object to save
     */
    @Override
    public void update(final TEntity entity) {
        dataEntity.getDataEntity(entity);
        String query = QueryGeneration.getUpdateQuery(dataEntity);

        statement = Connector.getStatement(query, WITHOUT_P_KEY, connection);
        Connector.setValuesWithPk(dataEntity.getValues(),
                dataEntity.getId(), statement);

        Connector.commit(connection, statement);
    }

    /**
     * Find record in database by id.
     *
     * @param aClass class object
     * @param id     primary key
     * @return object which found in database
     */
    @Override
    public TEntity get(final Class<TEntity> aClass, final Integer id) {
        String query = QueryGeneration.getSelectQuery(aClass);

        statement = Connector.getStatement(query, WITHOUT_P_KEY, connection);
        Connector.setValuePk(id, statement);
        ResultSet rs = Connector.execute(statement);

        return Connector.getEntity(rs, aClass);
    }

    /**
     * Find all records in database.
     *
     * @param aClass class object
     * @return list objects which found in database
     */
    @Override
    public List<TEntity> getAll(final Class<TEntity> aClass) {
        String query = QueryGeneration.getSelectAllQuery(aClass);

        statement = Connector.getStatement(query, WITHOUT_P_KEY, connection);
        ResultSet rs = Connector.execute(statement);

        return Connector.getListEntity(rs, aClass);
    }

    /**
     * Delete record in database by id.
     *
     * @param id     primary key
     * @param aClass class object
     */
    @Override
    public void delete(final Class<TEntity> aClass, final Integer id) {
        String query = QueryGeneration.getDeleteQuery(aClass);

        statement = Connector.getStatement(query,
                WITHOUT_P_KEY,
                connection);
        Connector.setValuePk(id, statement);
        Connector.commit(connection, statement);
    }

    /**
     * Delete all record in database.
     *
     * @param aClass class object
     */
    @Override
    public void deleteAll(final Class<TEntity> aClass) {
        String query = QueryGeneration.getDeleteAllQuery(aClass);

        statement = Connector.getStatement(query,
                WITHOUT_P_KEY,
                connection);
        Connector.commit(connection, statement);
    }
}
