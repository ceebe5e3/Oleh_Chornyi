package ua.nure.chornyi.SummaryTask4.web.command;

import ua.nure.chornyi.SummaryTask4.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public abstract class Command {
    public abstract String execute(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException, ServletException, AppException;
    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }

}
