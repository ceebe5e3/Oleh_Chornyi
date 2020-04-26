package ua.nure.chornyi.SummaryTask4.web.filter;

import org.apache.log4j.Logger;
import ua.nure.chornyi.SummaryTask4.Path;
import ua.nure.chornyi.SummaryTask4.db.entity.enums.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Access filter.
 */
public class AccessFilter implements Filter {

    private static final Logger logger = Logger.getLogger(AccessFilter.class);

    // commands access
    private static Map<Role, List<String>> accessMap = new HashMap<Role, List<String>>();
    private static List<String> adminCommands = new ArrayList<String>();
    private static List<String> subscriberCommands = new ArrayList<String>();
    private static List<String> commonCommands = new ArrayList<String>();
    private static List<String> loginCommands = new ArrayList<String>();

    public void destroy() {
        logger.debug("Filter destruction starts");
        logger.debug("Filter destruction finished");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.debug("Filter starts");
        if (accessAllowed(request)) {
            logger.debug("Filter finished");
            chain.doFilter(request, response);
        } else {
            String errorMessage = "You do not have permission to access the requested resource";

            request.setAttribute("errorMessage", errorMessage);
            logger.trace("Set the request attribute: errorMessage ==> " + errorMessage);

            request.getRequestDispatcher(Path.ERROR_PAGE).forward(request, response);
        }
    }

    private boolean accessAllowed(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String commandName = request.getParameter("command");
        logger.trace("Command name ==> " + commandName);
        if (commandName == null || commandName.isEmpty()) {
            return false;
        }
        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return false;
        }

        Role userRole = (Role) session.getAttribute("userRole");
        if (userRole == null) {
            return loginCommands.contains(commandName);
        }
        return accessMap.get(userRole).contains(commandName) || commonCommands.contains(commandName);
    }

    public void init(FilterConfig filterConfig) {
        logger.debug("Filter initialization starts");

        // roles
        adminCommands = createList(filterConfig.getInitParameter("admin"));
        subscriberCommands = createList(filterConfig.getInitParameter("subscriber"));

        // access map
        accessMap.put(Role.ADMIN, adminCommands);
        accessMap.put(Role.SUBSCRIBER, subscriberCommands);
        logger.trace("Access map ==> " + accessMap);

        // commons
        commonCommands = createList(filterConfig.getInitParameter("common"));
        logger.trace("Common commands ==> " + commonCommands);

        // login
        loginCommands = createList(filterConfig.getInitParameter("login"));
        logger.trace("Login commands ==> " + loginCommands);

        logger.debug("Filter initialization finished");
    }

    private List<String> createList(String string) {
        List<String> list = new ArrayList<String>();
        String[] commands = string.split(" ");
        for (String command : commands) {
            list.add(command);
        }
        return list;
    }
}
