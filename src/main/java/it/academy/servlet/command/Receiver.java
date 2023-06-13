package it.academy.servlet.command;

import java.util.HashMap;

import static it.academy.util.DataUI.DELETE_ALL_COMMAND;
import static it.academy.util.DataUI.DELETE_COMMAND;
import static it.academy.util.DataUI.GET_ALL_COMMAND;
import static it.academy.util.DataUI.SAVE_COMMAND;
import static it.academy.util.DataUI.UPDATE_COMMAND;

/**
 * @author Katerina
 * @version 1.0
 */
public class Receiver {
    /**
     * Receiver.
     */
    private static Receiver instance = null;
    /**
     * HashMap all variants commands.
     */
    private static final HashMap<String, Command> COMMANDS = new HashMap<>();

    /**
     * Get Receiver.
     *
     * @return current receiver
     */
    public static Receiver getInstance() {
        if (instance == null) {
            instance = new Receiver();
        }
        return instance;
    }

    /**
     * Default constructor.
     */
    public Receiver() {
        COMMANDS.put(GET_ALL_COMMAND, new GetAllCommand());
        COMMANDS.put(SAVE_COMMAND, new SaveCommand());
        COMMANDS.put(UPDATE_COMMAND, new UpdateCommand());
        COMMANDS.put(DELETE_COMMAND, new DeleteCommand());
        COMMANDS.put(DELETE_ALL_COMMAND, new DeleteAllCommand());
    }

    /**
     * Get implements command.
     *
     * @param nameCommand name command
     * @return object command
     */
    public Command getCommand(final String nameCommand) {
        return COMMANDS.get(nameCommand);
    }
}
