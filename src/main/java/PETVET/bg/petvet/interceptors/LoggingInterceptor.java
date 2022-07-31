package PETVET.bg.petvet.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;


public class LoggingInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuilder result = new StringBuilder("\nPREHANDLE:").append(System.lineSeparator());
        result.append(request.getRequestURL()).append(" ").append(request.getMethod()).append(System.lineSeparator());
        Iterator<String> iter = request.getHeaderNames().asIterator();
        while (iter.hasNext()) {
            String headerName = iter.next();
            String headerValue = request.getHeader(headerName);
            result.append(headerName).append(": ").append(headerValue).append(System.lineSeparator());
        }
        LOGGER.info(result.toString());
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        StringBuilder result = new StringBuilder("\nAFTERCOMPLETION:").append(System.lineSeparator());
        if(ex != null) {
            result.append("Error message: ").append(ex.getMessage()).append(System.lineSeparator());
            result.append("URL: ").append(request.getRequestURL()).append(System.lineSeparator());
        }
        LOGGER.info(result.toString());

        response.setStatus(200);
    }
}
