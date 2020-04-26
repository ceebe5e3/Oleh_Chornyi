package ua.nure.chornyi.SummaryTask4.web.filter;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.mockito.Mockito;

public class EncodingFilterTest {

    static EncodingFilter encodingFilter;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        encodingFilter = new EncodingFilter();
    }

    @Test
    public void testMock() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        FilterChain chain = Mockito.mock(FilterChain.class);

        encodingFilter.doFilter(request, response, chain);

        FilterConfig fConfig = Mockito.mock(FilterConfig.class);
        encodingFilter.init(fConfig);
        encodingFilter.destroy();
    }

}