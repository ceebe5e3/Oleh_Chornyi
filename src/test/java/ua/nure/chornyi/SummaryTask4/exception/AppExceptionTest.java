package ua.nure.chornyi.SummaryTask4.exception;

import org.junit.Test;

public class AppExceptionTest {

    @Test(expected=AppException.class)
    public void testAppException() throws AppException {
        throw new AppException();
    }

    @Test(expected=AppException.class)
    public void testAppExceptionStringThrowable() throws AppException {
        throw new AppException("Message", new Throwable());
    }

    @Test(expected=AppException.class)
    public void testAppExceptionString() throws AppException {
        throw new AppException("Messge");
    }

}