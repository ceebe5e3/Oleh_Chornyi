package ua.nure.chornyi.SummaryTask4.db.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class TariffTest {

    private final Tariff tariff = new Tariff();

    @Test
    public void getName() {
        assertEquals(tariff.getName(), "Tariff");
    }

    @Test
    public void setName() {
        tariff.setName("Tariff1");
        assertEquals(tariff.getName(), "Tariff1");
    }
}