package flow.filter;

import flow.filter.handler.HandlerMapping;
import flow.filter.invoker.MethodInvoker;
import flow.resolver.request.RequestResolver;
import flow.resolver.request.RequestResolverFactory;
import flow.resolver.response.ResponseResolver;
import flow.resolver.response.ResponseResolverFactory;

import javax.management.ServiceNotFoundException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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

        if(isAccessible(method)) {
            try {
                final MethodInvoker handler = handlerMapping.getHandler(request);
                RequestResolver resolver = RequestResolverFactory.getResolver(request);
                final Object invoke = handler.invoke(resolver.getParameter(request, handler.getMethod()));
                final ResponseResolver responseResolver = ResponseResolverFactory.getResponseResolver(handler);
                responseResolver.resolve(request, servletResponse, invoke);
            } catch (ServiceNotFoundException e) {
                throw new ServletException("존재하지 않는 URI입니다.");
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new IOException("처리하던 중 오류가 발생했습니다." + e.getMessage());
            }
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
    
    private boolean isAccessible(String method){
        return "GET".equals(method) || "POST".equals(method);
    }

}
