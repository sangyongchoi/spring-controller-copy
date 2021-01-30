package flow.resolver.request;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DefaultRequestResolver extends RequestResolver{
    @Override
    protected String getInputData(HttpServletRequest request) throws IOException {

        return "{}";
    }
}
