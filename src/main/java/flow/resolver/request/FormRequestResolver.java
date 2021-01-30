package flow.resolver.request;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

public class FormRequestResolver extends RequestResolver{
    @Override
    protected String getInputData(HttpServletRequest request) throws IOException {
        final Enumeration<String> parameterNames = request.getParameterNames();
        StringBuilder sb = new StringBuilder();

        sb.append("{");
        while (parameterNames.hasMoreElements()) {
            final String s = parameterNames.nextElement();
            sb.append("\"").append(s).append("\" : \"").append(request.getParameter(s)).append("\",");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        sb.append("}");

        return sb.toString();
    }
}
