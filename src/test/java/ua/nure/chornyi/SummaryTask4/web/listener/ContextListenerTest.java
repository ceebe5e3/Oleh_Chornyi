package ua.nure.chornyi.SummaryTask4.web.listener;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.ServletContextEvent;

public class ContextListenerTest {

    static ContextListener contextListener;

    @Test
    public void contextInitialized() {
        contextListener = new ContextListener();
    }

    @Test
    public void contextDestroyed() {
        ServletContextEvent contextEvent = Mockito.mock(ServletContextEvent.class);
        contextListener.contextDestroyed(contextEvent);
    }
}