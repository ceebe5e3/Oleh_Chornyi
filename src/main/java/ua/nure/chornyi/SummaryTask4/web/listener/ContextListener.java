package ua.nure.chornyi.SummaryTask4.web.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Arrays;
import java.util.List;

/**
 * Context listener.
 */
public class ContextListener implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log("Servlet context initialization starts");
        ServletContext servletContext = servletContextEvent.getServletContext();
        initLog4J(servletContext);
        initCommandContainer();
        initI18N(servletContext);

        log("Servlet context initialization finished");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log("Servlet context destruction starts");
        log("Servlet context destruction finished");
    }

    private void initLog4J(ServletContext servletContext) {
        log("Log4J initialization started");
        try {
            PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
            logger.debug("Log4j has been initialized");
        } catch (Exception ex) {
            log("Cannot configure Log4j");
            ex.printStackTrace();
        }
        log("Log4J initialization finished");
    }

    private void initCommandContainer() {
        try {
            Class.forName("ua.nure.chornyi.SummaryTask4.web.command.CommandContainer");
        } catch (ClassNotFoundException ex) {
            throw new IllegalStateException("Cannot initialize Command Container");
        }
    }

    private void log(String msg) {
        System.out.println("[ContextListener] " + msg);
    }

    private void initI18N(ServletContext servletContext) {
        logger.debug("I18N subsystem initialization started");

        List<String> locales = Arrays.asList(servletContext.getInitParameter("locales").split(" "));

        logger.debug("Application attribute set: 'locales' = " + locales);
        servletContext.setAttribute("locales", locales);

        logger.debug("I18N subsystem initialization finished");
    }
}
