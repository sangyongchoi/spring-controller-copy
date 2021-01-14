package flow.filter;

import javax.servlet.*;
import java.io.IOException;

public class ControllerFilter implements Filter {

    HandlerMapping handlerMapping = new HandlerMapping();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        handlerMapping.init();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    }

    @Override
    public void destroy() {
    }
}
