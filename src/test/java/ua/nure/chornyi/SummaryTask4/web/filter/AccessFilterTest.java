package ua.nure.chornyi.SummaryTask4.web.filter;

import org.junit.BeforeClass;
import org.junit.Test;

public class AccessFilterTest {

    static AccessFilter accessFilter;

    @BeforeClass
    public static void setUpBeforeClass(){
        accessFilter = new AccessFilter();
    }

    @Test
    public void testDestroy() {
        accessFilter.destroy();
    }
}