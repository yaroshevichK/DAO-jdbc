package it.academy.servlet.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Katerina
 * @version 1.0
 */
public interface Command {
    /**
     * Execute command.
     *
     * @param req  servlet request
     * @param resp servlet response
     * @return request page
     */
    String execute(HttpServletRequest req, HttpServletResponse resp);
}
