package flow.filter.handler;

import flow.annotation.Controller;
import flow.annotation.GetMapping;
import flow.annotation.PostMapping;
import flow.annotation.RestController;
import flow.exception.HandlerCreateException;
import flow.exception.NotFoundException;
import flow.exception.code.ErrorCode;
import flow.filter.invoker.MethodInvoker;

import javax.management.ServiceNotFoundException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {

    Map<String, MethodInvoker> controller = new HashMap<>();
    Map<String, MethodInvoker> restController = new HashMap<>();

    public void init(Class<?> tClass){
        subDirList(tClass.getResource(".").getPath());
    }

    private void subDirList(String source) {
        File dir = new File(source);
        File[] fileList = dir.listFiles();

        if (fileList != null) {
            try {
                for (File file : fileList) {
                    if (file.isDirectory()) {
                        subDirList(file.getCanonicalPath());
                    } else if (file.isFile()) {
                        String path = file.getPath();

                        if (path.endsWith(".class")) {
                            int classes = path.lastIndexOf("classes");
                            String substring = path.substring(classes + 8);
                            String className = substring.split(".class")[0].replace("\\", ".");
                            Class<?> aClass = Class.forName(className);
                            if (aClass.isAnnotationPresent(Controller.class)) {
                                Method[] methods = aClass.getMethods();
                                Arrays.stream(methods)
                                        .forEach(m -> addPageHandler(aClass, m));
                            } else if (aClass.isAnnotationPresent(RestController.class)) {
                                Method[] methods = aClass.getMethods();
                                Arrays.stream(methods)
                                        .forEach(m -> addRestHandler(aClass, m));
                            }
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new HandlerCreateException(ErrorCode.ERROR_HANDLER_CREATE);
            }
        }
    }

    private void addPageHandler(Class<?> aClass, Method m) {
        if (m.isAnnotationPresent(PostMapping.class)) {
            addPostHandler(aClass, m, RequestType.PAGE);
        } else if (m.isAnnotationPresent(GetMapping.class)) {
            addGetHandler(aClass, m, RequestType.PAGE);
        }
    }

    private void addRestHandler(Class<?> aClass, Method m) {
        if (m.isAnnotationPresent(PostMapping.class)) {
            addPostHandler(aClass, m, RequestType.POST);
        } else if (m.isAnnotationPresent(GetMapping.class)) {
            addGetHandler(aClass, m, RequestType.GET);
        }
    }

    private void addPostHandler(Class<?> aClass, Method m, RequestType requestType){
        PostMapping declaredAnnotation = m.getDeclaredAnnotation(PostMapping.class);
        String value = declaredAnnotation.value();
        addHandler(aClass, m, requestType, value);
    }

    private void addGetHandler(Class<?> aClass, Method m, RequestType requestType){
        GetMapping declaredAnnotation = m.getDeclaredAnnotation(GetMapping.class);
        String value = declaredAnnotation.value();
        addHandler(aClass, m, requestType, value);
    }

    private void addHandler(Class<?> aClass, Method m, RequestType requestType, String value){
        MethodInvoker methodInvoker = new MethodInvoker(aClass, m, requestType);
        if (isHandlerExists(value)) {
            throw new HandlerCreateException(value + " 중복된 URI입니다", ErrorCode.ERROR_HANDLER_CREATE);
        }

        if(RequestType.PAGE.equals(requestType)){
            controller.put(value, methodInvoker);
        }else{
            restController.put(value, methodInvoker);
        }
    }

    private boolean isHandlerExists(String key){
        return restController.get(key) != null || controller.get(key) != null;
    }

    public MethodInvoker getHandler(String requestURI) {
        if (isApiRequest(requestURI)) {
            return restController.get(requestURI);
        } else if (isPageRequest(requestURI)) {
            return controller.get(requestURI);
        } else {
            throw new NotFoundException(ErrorCode.ERROR_HANDLER_NOT_FOUND);
        }
    }

    private boolean isPageRequest(String requestURI) {
        return controller.containsKey(requestURI);
    }

    private boolean isApiRequest(String requestURI) {
        return restController.containsKey(requestURI);
    }
}
