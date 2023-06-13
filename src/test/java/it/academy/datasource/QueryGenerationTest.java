package it.academy.datasource;

import it.academy.entity.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static it.academy.Constant.DELETE_ALL_QUERY;
import static it.academy.Constant.DELETE_QUERY;
import static it.academy.Constant.INSERT_QUERY;
import static it.academy.Constant.NOT_CORRECT_QUERY;
import static it.academy.Constant.SELECT_ALL_QUERY;
import static it.academy.Constant.SELECT_QUERY;
import static it.academy.Constant.UPDATE_QUERY;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryGenerationTest {
    private final static DataEntity<Person> dataEntity = new DataEntity<>();
    private static Person person;

    @BeforeAll
    public static void init() {
        person = new Person();
        dataEntity.getDataEntity(person);
    }

    @Test
    void isShouldCreateInsertSQLQuery() {
        String query = QueryGeneration.getInsertQuery(dataEntity);
        assertEquals(query, INSERT_QUERY, NOT_CORRECT_QUERY);
    }

    @Test
    void isShouldCreateUpdateSQLQuery() {
        String query = QueryGeneration.getUpdateQuery(dataEntity);
        assertEquals(query, UPDATE_QUERY, NOT_CORRECT_QUERY);
    }

    @Test
    void isShouldCreateSelectSQLQuery() {
        String query = QueryGeneration.getSelectQuery(Person.class);
        assertEquals(query, SELECT_QUERY, NOT_CORRECT_QUERY);
    }

    @Test
    void isShouldCreateSelectAllSQLQuery() {
        String query = QueryGeneration.getSelectAllQuery(Person.class);
        assertEquals(query, SELECT_ALL_QUERY, NOT_CORRECT_QUERY);
    }

    @Test
    void isShouldCreateDeleteSQLQuery() {
        String query = QueryGeneration.getDeleteQuery(Person.class);
        assertEquals(query, DELETE_QUERY, NOT_CORRECT_QUERY);
    }

    @Test
    void isShouldCreateDeleteAllSQLQuery() {
        String query = QueryGeneration.getDeleteAllQuery(Person.class);
        assertEquals(query, DELETE_ALL_QUERY, NOT_CORRECT_QUERY);
    }
}