package flow.resolver.response;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ViewResponseResolver implements ResponseResolver{

    private static final String CONTENT_TYPE = "text/html";

    @Override
    public void resolve(HttpServletRequest request, ServletResponse response, Object viewName) throws ServletException, IOException {
        response.setContentType(ViewResponseResolver.CONTENT_TYPE);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewName.toString());
        requestDispatcher.forward(request, response);
    }
}
