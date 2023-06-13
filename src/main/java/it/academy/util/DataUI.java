package it.academy.util;

/**
 * @author Katerina
 * @version 1.0
 */
public final class DataUI {
    /**
     * Default constructor.
     */
    private DataUI() {
    }

    /**
     * Unicode UTF-8.
     */
    public static final String UTF_8 = "UTF-8";
    /**
     * Get method.
     */
    public static final String GET = "GET";
    /**
     * Post method.
     */
    public static final String POST = "POST";


    /**
     * Name command parameter.
     */
    public static final String PARAM_COMMAND = "command";
    /**
     * Name get all command.
     */
    public static final String GET_ALL_COMMAND = "getAll";
    /**
     * Name save command.
     */
    public static final String SAVE_COMMAND = "save";
    /**
     * Name update command.
     */
    public static final String UPDATE_COMMAND = "update";
    /**
     * Name delete command.
     */
    public static final String DELETE_COMMAND = "delete";
    /**
     * Name delete all command.
     */
    public static final String DELETE_ALL_COMMAND = "deleteAll";

    /**
     * Name request parameter people.
     */
    public static final String PARAM_PEOPLE = "people";
    /**
     * Name request parameter primary key.
     */
    public static final String PARAM_ID = "id";
    /**
     * Name request parameter name person.
     */
    public static final String PARAM_NAME = "name";
    /**
     * Name request parameter surname person.
     */
    public static final String PARAM_SURNAME = "surname";
    /**
     * Name request parameter gender person.
     */
    public static final String PARAM_GENDER = "gender";

    /**
     * Name URL for filter.
     */
    public static final String FILTER_URL = "/*";
    /**
     * Main URL.
     */
    public static final String MAIN_URL = "/people";

    /**
     * List people jsp.
     */
    public static final String PEOPLE_PATH = "jsp/list.jsp";
    /**
     * Add person jsp.
     */
    public static final String SAVE_PERSON_PATH = "jsp/save.jsp";
    /**
     * Edit person jsp.
     */
    public static final String UPDATE_PERSON_PATH = "jsp/update.jsp";
    /**
     * Error jsp.
     */
    public static final String ERROR_PATH = "jsp/error.jsp";
}
