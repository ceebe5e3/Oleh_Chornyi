package ua.nure.chornyi.SummaryTask4.db.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {

    private final Service service = new Service();

    @Test
    public void getName() {
        assertEquals(service.getName(), "Service");
    }

    @Test
    public void setName() {
        service.setName("Service1");
        assertEquals(service.getName(), "Service1");
    }
}