package it.academy.datasource;

import it.academy.dto.GenderDto;
import it.academy.entity.Gender;
import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static it.academy.util.DataDB.COLUMN;
import static it.academy.util.DataDB.ID;
import static it.academy.util.DataDB.TABLE;

/**
 * @param <TEntity> type
 * @author Katerina
 * @version 1.0
 */
@Getter
public class DataEntity<TEntity> {
    /**
     * name table in database.
     */
    private String tableName;

    /**
     * field with annotation @Id.
     */
    private Field fieldId;

    /**
     * value primary key in Entity.
     */
    private Integer id;

    /**
     * HashMap: key - field in Entity, value - value in field.
     */
    private Map<Field, Object> fields;

    /**
     * Get name table in database.
     *
     * @param clsEntity class Entity
     * @param <TEntity> type Entity
     * @return name table
     */
    public static <TEntity> String getTableName(
            final Class<TEntity> clsEntity) {
        return clsEntity.getAnnotation(TABLE).name();
    }

    /**
     * Get name primary key field.
     *
     * @param clsEntity class Entity
     * @param <TEntity> type Entity
     * @return name field
     */
    public static <TEntity> String getNameFieldId(
            final Class<TEntity> clsEntity) {

        return Arrays.stream(clsEntity.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(ID))
                .map(field -> field.getAnnotation(ID).name())
                .findFirst()
                .orElse(null);
    }

    /**
     * Get all fields Entity with primary key.
     *
     * @param clsEntity class Entity
     * @param <TEntity> type Entity
     * @return ArrayList with Field Entity
     */
    private static <TEntity> List<Field> getAllFields(
            final Class<TEntity> clsEntity) {

        return Arrays.stream(clsEntity.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(ID)
                        || field.isAnnotationPresent(COLUMN))
                .toList();
    }

    /**
     * Set database value in Entity.
     *
     * @param resultSet  result query in database
     * @param entity     object Entity
     * @param listFields list fields
     * @param <TEntity>  type Entity
     */
    private static <TEntity> void setValues(final ResultSet resultSet,
                                            final TEntity entity,
                                            final List<Field> listFields) {
        for (Field field : listFields) {
            String nameColumn = field.isAnnotationPresent(ID)
                    ? field.getAnnotation(ID).name()
                    : field.getAnnotation(COLUMN).name();

            try {
                Object value = resultSet.getObject(nameColumn);
                field.setAccessible(true);
                if (field.getType().equals(Gender.class)) {
                    Gender gender = getGender((String) value);
                    field.set(entity, gender);
                } else {
                    field.set(entity, value);
                }
                field.setAccessible(false);
            } catch (SQLException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Create object Entity with database values.
     *
     * @param resultSet result query in database
     * @param clsEntity class Entity
     * @param <TEntity> type Entity
     * @return Object Entity
     */
    public static <TEntity> TEntity getEntity(final ResultSet resultSet,
                                              final Class<TEntity> clsEntity) {
        TEntity entity = null;
        List<Field> listFields = getAllFields(clsEntity);

        try {
            if (resultSet.next()) {
                entity = clsEntity.getDeclaredConstructor().newInstance();
                setValues(resultSet, entity, listFields);
            }
        } catch (SQLException | NoSuchMethodException
                 | InstantiationException | IllegalAccessException
                 | InvocationTargetException e) {
            e.printStackTrace();
        }

        return entity;
    }

    /**
     * Create list objects Entity with database values.
     *
     * @param resultSet result query in database
     * @param clsEntity class Entity
     * @param <TEntity> type Entity
     * @return List objects Entity
     */
    public static <TEntity> List<TEntity> getListEntity(
            final ResultSet resultSet,
            final Class<TEntity> clsEntity) {

        List<TEntity> listEntity = new ArrayList<>();
        List<Field> listFields = getAllFields(clsEntity);

        try {
            while (resultSet.next()) {
                TEntity entity = clsEntity
                        .getDeclaredConstructor()
                        .newInstance();

                setValues(resultSet, entity, listFields);
                listEntity.add(entity);
            }
        } catch (SQLException | InvocationTargetException
                 | InstantiationException | IllegalAccessException
                 | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return listEntity;
    }

    /**
     * Get name primary key field in database.
     *
     * @return name field
     */
    public String getFieldId() {
        return fieldId.getAnnotation(ID).name();
    }

    /**
     * Get list names fields in database.
     *
     * @return list fields
     */
    public List<String> getStringFields() {
        return fields
                .keySet()
                .stream()
                .map(field -> field.getAnnotation(COLUMN).name())
                .toList();
    }

    /**
     * Get list values fields in current dataEntity.
     *
     * @return list values fields.
     */
    public List<Object> getValues() {
        return fields
                .values()
                .stream()
                .toList();
    }

    /**
     * Initialization data Entity.
     *
     * @param entity object Entity
     */
    public void getDataEntity(final TEntity entity) {
        fieldId = null;
        fields = new HashMap<>();
        tableName = getTableName(entity.getClass());

        Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(ID)
                        || field.isAnnotationPresent(COLUMN))
                .forEach(field -> {
                    if (field.isAnnotationPresent(COLUMN)
                            && !field.isAnnotationPresent(ID)) {

                        field.setAccessible(true);
                        try {
                            fields.put(field, field.get(entity));
                        } catch (IllegalAccessException e) {
                            fields.put(field, null);
                        }
                        field.setAccessible(false);
                    }

                    if (field.isAnnotationPresent(ID)) {
                        fieldId = field;

                        field.setAccessible(true);
                        try {
                            id = (Integer) field.get(entity);
                        } catch (IllegalAccessException e) {
                            id = null;
                        }
                        field.setAccessible(false);
                    }
                });

    }

    /**
     * Set primary key in primary key field Entity.
     *
     * @param value  value primary key
     * @param entity object Entity
     */
    public void setId(final Integer value, final TEntity entity) {
        try {
            fieldId.setAccessible(true);
            fieldId.set(entity, value);
            fieldId.setAccessible(false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get gender of String.
     *
     * @param value value gender
     * @return enum object gender or null if not find
     */
    private static Gender getGender(final String value) {
        if (GenderDto.MALE.name().equals(value)) {
            return Gender.MALE;
        } else if (GenderDto.FEMALE.name().equals(value)) {
            return Gender.FEMALE;
        }
        return null;
    }
}
