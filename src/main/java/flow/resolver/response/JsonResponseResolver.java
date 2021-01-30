package flow.resolver.response;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonResponseResolver implements ResponseResolver{

    private static final String CONTENT_TYPE = "application/json";

    @Override
    public void resolve(HttpServletRequest request, ServletResponse response, Object result) throws ServletException, IOException {
        response.setContentType(JsonResponseResolver.CONTENT_TYPE);

        try (PrintWriter writer = response.getWriter()) {
            writer.write("{ \"result\": \"" + result.toString() + "\"}");
            writer.flush();
        }
    }
}
