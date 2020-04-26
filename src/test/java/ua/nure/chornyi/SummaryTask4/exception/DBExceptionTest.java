package ua.nure.chornyi.SummaryTask4.exception;

import org.junit.Test;

public class DBExceptionTest {

    @Test(expected = DBException.class)
    public void testDBException() throws DBException {
        throw new DBException();
    }

    @Test(expected = DBException.class)
    public void testDBExceptionStringThrowable() throws DBException {
        throw new DBException("Message", new Throwable());
    }

}