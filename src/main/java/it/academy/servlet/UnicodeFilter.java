package it.academy.servlet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

import static it.academy.util.DataUI.FILTER_URL;
import static it.academy.util.DataUI.UTF_8;

/**
 * @author Katerina
 * @version 1.0
 */
@WebFilter(urlPatterns = {FILTER_URL})
public class UnicodeFilter implements Filter {
    /**
     * Set character unicode.
     *
     * @param servletRequest  request
     * @param servletResponse response
     * @param filterChain     filter chain
     * @throws IOException      exception
     * @throws ServletException exception
     */
    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        servletRequest.setCharacterEncoding(UTF_8);
        servletResponse.setCharacterEncoding(UTF_8);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
