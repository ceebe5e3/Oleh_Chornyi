package ua.nure.chornyi.SummaryTask4.web.command.common;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.exception.AppException;
import ua.nure.chornyi.SummaryTask4.web.command.Command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Logout command.
 */
public class LogoutCommand extends Command {

    private static final Logger logger = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        logger.debug("Logout command starts");

        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }

        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if (cookie.getName().equals("login")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return Path.PAGE_LOGIN;
    }
}
