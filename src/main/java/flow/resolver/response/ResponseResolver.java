package flow.resolver.response;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface ResponseResolver {
    void resolve(HttpServletRequest request, ServletResponse response, Object viewName) throws ServletException, IOException;
}
