package ua.nure.chornyi.SummaryTask4.web.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * Encoding filter. UTF-8 encoding.
 */
public class EncodingFilter implements Filter {
    private static final Logger logger = Logger.getLogger(EncodingFilter.class);

    private String encoding;

    @Override
    public void destroy() {
        logger.debug("Filter destruction starts");
        logger.debug("Filter destruction finished");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("Filter starts");
        if (null == servletRequest.getCharacterEncoding()) {
            servletRequest.setCharacterEncoding(encoding);
        }
        servletResponse.setContentType("text/html; charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        logger.debug("Filter finished");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig){
        logger.debug("Filter initialization starts");
        encoding = filterConfig.getInitParameter("requestEncoding");
        logger.trace("Request encoding: " + encoding);
        if (encoding == null) {
            encoding = "UTF-8";
        }
        logger.debug("Filter initialization finished");
    }
}
