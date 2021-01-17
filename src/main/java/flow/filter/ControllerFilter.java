package flow.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

public class ControllerFilter implements Filter {

    HandlerMapping handlerMapping = new HandlerMapping();

    @Override
    public void init(FilterConfig filterConfig) {
        handlerMapping.init();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String method = request.getMethod();
        String requestURI = request.getRequestURI();

        if("GET".equals(method) || "POST".equals(method)) {
            if (handlerMapping.isPageRequest(requestURI)) {
                Object invoke = handlerMapping.invoke(request);
                RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher(invoke.toString());
                requestDispatcher.forward(servletRequest, servletResponse);
            } else if (handlerMapping.isApiRequest(requestURI)) {
                Object invoke = handlerMapping.invoke(request);
                servletResponse.setContentType("application/json");

                try (PrintWriter writer = servletResponse.getWriter()) {
                    writer.write("{ \"result\": \"" + invoke.toString() + "\"}");
                    writer.flush();
                }
            }
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }

}
