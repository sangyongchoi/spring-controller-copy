package flow.resolver.request;

import javax.servlet.http.HttpServletRequest;

public class RequestResolverFactory {

    private static final DefaultRequestResolver defaultRequestResolver = new DefaultRequestResolver();
    private static final JsonRequestResolver jsonRequestResolver = new JsonRequestResolver();
    private static final FormRequestResolver formRequestResolver = new FormRequestResolver();

    public static RequestResolver getResolver(HttpServletRequest request) {
        String contentType = request.getContentType();

        if (contentType == null) {
            return defaultRequestResolver;
        } else if (contentType.contains("application/json")) {
            return jsonRequestResolver;
        } else if(contentType.contains("application/x-www-form-urlencoded")){
            return formRequestResolver;
        }

        return defaultRequestResolver;
    }
}
