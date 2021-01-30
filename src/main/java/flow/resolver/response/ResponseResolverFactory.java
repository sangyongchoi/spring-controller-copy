package flow.resolver.response;

import flow.filter.handler.RequestType;
import flow.filter.invoker.MethodInvoker;

public class ResponseResolverFactory {

    private static final ViewResponseResolver viewResponseResolver = new ViewResponseResolver();
    private static final JsonResponseResolver jsonResponseResolver = new JsonResponseResolver();

    public static ResponseResolver getResponseResolver(MethodInvoker methodInvoker){
        if (RequestType.PAGE.equals(methodInvoker.getRequestType())) {
            return viewResponseResolver;
        } else {
            return jsonResponseResolver;
        }
    }
}
