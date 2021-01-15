package flow.filter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInvoker {

    private String path;
    private MethodType methodType;
    private Class<?> aClass;
    private Method method;

    public MethodInvoker(String path, MethodType methodType, Class<?> aClass, Method method) {
        this.path = path;
        this.methodType = methodType;
        this.aClass = aClass;
        this.method = method;
    }

    public Object invoke() throws InvocationTargetException, IllegalAccessException, InstantiationException {
        try{
            return method.invoke(aClass.newInstance());
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
