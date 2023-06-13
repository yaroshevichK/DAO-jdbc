package it.academy.servlet;

import it.academy.servlet.command.Command;
import it.academy.servlet.command.Receiver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static it.academy.util.DataUI.MAIN_URL;
import static it.academy.util.DataUI.PARAM_COMMAND;

/**
 * @author Katerina
 * @version 1.0
 */
@WebServlet(urlPatterns = {MAIN_URL})
public class MainServlet extends HttpServlet {
    /**
     * Receiver.
     */
    private static final Receiver RECEIVER = Receiver.getInstance();

    /**
     * Get method.
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException servlet exception
     * @throws IOException      io exception
     */
    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    /**
     * Post method.
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException servlet exception
     * @throws IOException      io exception
     */
    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    /**
     * Execute command.
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException servlet exception
     * @throws IOException      io exception
     */
    protected void process(final HttpServletRequest req,
                           final HttpServletResponse resp)
            throws ServletException, IOException {
        Command command = RECEIVER.getCommand(req.getParameter(PARAM_COMMAND));
        String path = command.execute(req, resp);
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
