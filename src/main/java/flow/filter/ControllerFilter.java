package flow.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ControllerFilter implements Filter {

    HandlerMapping handlerMapping = new HandlerMapping();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        handlerMapping.init();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String method = request.getMethod();
        String requestURI = request.getRequestURI();
        if (handlerMapping.isPageRequest(requestURI)) {
            Object invoke = handlerMapping.invoke(requestURI);
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher(invoke.toString());
            requestDispatcher.forward(servletRequest, servletResponse);
        } else if (handlerMapping.isApiRequest(requestURI)) {
            Object invoke = handlerMapping.invoke(requestURI);
        } else {
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/hello.html");
            requestDispatcher.forward(servletRequest, servletResponse);
            //filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
