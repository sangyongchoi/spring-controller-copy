package flow.filter;

import flow.MainApplication;
import flow.annotation.Controller;
import flow.annotation.GetMapping;
import flow.annotation.PostMapping;
import flow.annotation.RestController;
import flow.common.CommonUtil;
import flow.dto.TestDto;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {

    Map<String, MethodInvoker> controller = new HashMap<>();
    Map<String, MethodInvoker> restController = new HashMap<>();

    public void init(){
        subDirList(MainApplication.class.getResource(".").getPath());
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
                e.printStackTrace();
            }
        }
    }

    private void addPageHandler(Class<?> aClass, Method m) {
        if (m.isAnnotationPresent(PostMapping.class)) {
            addPostHandler(aClass, m, MethodType.PAGE);
        } else if (m.isAnnotationPresent(GetMapping.class)) {
            addGetMapping(aClass, m, MethodType.PAGE);
        }
    }

    private void addRestHandler(Class<?> aClass, Method m) {
        if (m.isAnnotationPresent(PostMapping.class)) {
            addPostHandler(aClass, m, MethodType.POST);
        } else if (m.isAnnotationPresent(GetMapping.class)) {
            addGetMapping(aClass, m, MethodType.GET);
        }
    }

    private void addPostHandler(Class<?> aClass, Method m, MethodType methodType){
        PostMapping declaredAnnotation = m.getDeclaredAnnotation(PostMapping.class);
        String value = declaredAnnotation.value();
        MethodInvoker methodInvoker = new MethodInvoker(value, methodType, aClass, m);

        if(MethodType.PAGE.equals(methodType)){
            controller.put(value, methodInvoker);
        }else{
            restController.put(value, methodInvoker);
        }
    }

    private void addGetMapping(Class<?> aClass, Method m, MethodType methodType){
        GetMapping declaredAnnotation = m.getDeclaredAnnotation(GetMapping.class);
        String value = declaredAnnotation.value();
        MethodInvoker methodInvoker = new MethodInvoker(value, methodType, aClass, m);

        if(MethodType.PAGE.equals(methodType)){
            controller.put(value, methodInvoker);
        }else{
            restController.put(value, methodInvoker);
        }
    }

    public Object invoke(HttpServletRequest request) {
        try{
            String requestURI = request.getRequestURI();

            if (isApiRequest(requestURI)) {
                MethodInvoker methodInvoker = restController.get(requestURI);
                return methodInvoker.invoke(getParameter(request, methodInvoker.getMethod()));
            } else if (isPageRequest(requestURI)) {
                MethodInvoker methodInvoker = controller.get(requestURI);
                return methodInvoker.invoke(getParameter(request, methodInvoker.getMethod()));
            }
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean isPageRequest(String requestURI) {
        return controller.containsKey(requestURI);
    }

    public boolean isApiRequest(String requestURI) {
        return restController.containsKey(requestURI);
    }

    private Object[] getParameter(HttpServletRequest request, Method method) throws IOException {
        Parameter[] parameters = method.getParameters();
        Object[] requestParameter = new Object[parameters.length];

        String body = getBody(request);
        Object[] result = new Object[1];
        System.out.println(body);
        result[0] = CommonUtil.objectMapper.readValue(body, TestDto.class);

        return result;
//        for (int i = 0; i < parameters.length; i++) {
//            Parameter parameter = parameters[i];
//            String parameterName = parameter.getName();
//            String inputParameter = request.getParameter(parameterName);
//
//            try {
//                requestParameter[i] = CommonUtil.objectMapper.readValue(inputParameter, parameter.getClass());
//                System.out.println(inputParameter);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return requestParameter;
    }

    private String getBody(HttpServletRequest request) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();

        try(InputStream inputStream = request.getInputStream()) {
            if (inputStream != null) {
                try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                    char[] charBuffer = new char[128];
                    int bytesRead = -1;
                    while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                        stringBuilder.append(charBuffer, 0, bytesRead);
                    }
                }
            }
        }
        return stringBuilder.toString();
    }
}
