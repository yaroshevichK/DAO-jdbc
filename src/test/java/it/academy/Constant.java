package it.academy;

public class Constant {
    public static final String INSERT_QUERY =
            "INSERT INTO person (surname,gender,name) VALUES (?,?,?);";

    public static final String UPDATE_QUERY = "UPDATE person SET surname = ?,gender = ?,name = ? WHERE id = ?;";

    public static final String DELETE_QUERY = "DELETE FROM person WHERE id = ?;";

    public static final String DELETE_ALL_QUERY = "DELETE FROM person;";

    public static final String SELECT_QUERY = "SELECT * FROM person WHERE id = ?;";

    public static final String SELECT_ALL_QUERY = "SELECT * FROM person;";

    public static final String NOT_CORRECT_QUERY ="Query is not correct";
}
