package it.academy.datasource;

import java.util.List;
import java.util.stream.Collectors;

import static it.academy.util.DataQuery.CHAR_VALUE;
import static it.academy.util.DataQuery.DELETE_ALL_QUERY;
import static it.academy.util.DataQuery.DELETE_QUERY;
import static it.academy.util.DataQuery.DELIMITER_COMMA;
import static it.academy.util.DataQuery.DELIMITER_UPD;
import static it.academy.util.DataQuery.DELIM_LAST_UPD;
import static it.academy.util.DataQuery.INSERT_QUERY;
import static it.academy.util.DataQuery.SELECT_ALL_QUERY;
import static it.academy.util.DataQuery.SELECT_QUERY;
import static it.academy.util.DataQuery.UPDATE_QUERY;

/**
 * @author Katerina
 * @version 1.0
 */
public final class QueryGeneration {
    /**
     * Default constructor.
     */
    private QueryGeneration() {
    }

    /**
     * Generate query for insert record.
     *
     * @param dataEntity metadata entity
     * @param <TEntity>  type Entity
     * @return String with query.
     */
    public static <TEntity> String getInsertQuery(
            final DataEntity<TEntity> dataEntity) {
        List<String> fields = dataEntity.getStringFields();

        String values = fields.stream()
                .map(s -> CHAR_VALUE)
                .collect(Collectors.joining(DELIMITER_COMMA));

        return String.format(INSERT_QUERY, dataEntity.getTableName(),
                String.join(DELIMITER_COMMA, fields), values);
    }

    /**
     * Generate query for update record.
     *
     * @param dataEntity metadata entity
     * @param <TEntity>  type Entity
     * @return String with query.
     */
    public static <TEntity> String getUpdateQuery(
            final DataEntity<TEntity> dataEntity) {
        String fields = String.join(DELIMITER_UPD,
                dataEntity.getStringFields()) + DELIM_LAST_UPD;

        return String.format(UPDATE_QUERY, dataEntity.getTableName(),
                fields, dataEntity.getFieldId());
    }

    /**
     * Generate query for select query record by id field.
     *
     * @param <TEntity> type Entity
     * @param clsEntity class Entity
     * @return String with query.
     */
    public static <TEntity> String getSelectQuery(
            final Class<TEntity> clsEntity) {

        String nameTable = DataEntity.getTableName(clsEntity);
        String nameIdField = DataEntity.getNameFieldId(clsEntity);

        return String.format(SELECT_QUERY,
                nameTable, nameIdField);
    }

    /**
     * Generate query for select all records.
     *
     * @param <TEntity> type Entity
     * @param clsEntity class Entity
     * @return String with query.
     */
    public static <TEntity> String getSelectAllQuery(
            final Class<TEntity> clsEntity) {
        String nameTable = DataEntity.getTableName(clsEntity);
        return String.format(SELECT_ALL_QUERY, nameTable);
    }

    /**
     * Generate query for delete record by id.
     *
     * @param <TEntity> type Entity
     * @param clsEntity class Entity
     * @return String with query.
     */
    public static <TEntity> String getDeleteQuery(
            final Class<TEntity> clsEntity) {
        String nameTable = DataEntity.getTableName(clsEntity);
        String idField = DataEntity.getNameFieldId(clsEntity);

        return String.format(DELETE_QUERY, nameTable, idField);
    }

    /**
     * Generate query for delete all records.
     *
     * @param <TEntity> type Entity
     * @param clsEntity class Entity
     * @return String with query.
     */
    public static <TEntity> String getDeleteAllQuery(
            final Class<TEntity> clsEntity) {
        String nameTable = DataEntity.getTableName(clsEntity);
        return String.format(DELETE_ALL_QUERY, nameTable);
    }
}
