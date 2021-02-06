package flow.filter.invoker;

import flow.exception.MethodInvokeException;
import flow.exception.code.ErrorCode;
import flow.filter.handler.RequestType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInvoker {

    private Class<?> aClass;
    private Method method;
    private RequestType requestType;

    public MethodInvoker(Class<?> aClass, Method method, RequestType requestType) {
        this.aClass = aClass;
        this.method = method;
        this.requestType = requestType;
    }

    public Object invoke(Object[] parameter) {
        try{
            if(parameter.length == 0) {
                return method.invoke(aClass.newInstance());
            }else{
                return method.invoke(aClass.newInstance(), parameter);
            }
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new MethodInvokeException(e.getMessage(), ErrorCode.ERROR_METHOD_INVOKE);
        }
    }

    public Method getMethod() {
        return method;
    }

    public RequestType getRequestType() {
        return requestType;
    }
}
