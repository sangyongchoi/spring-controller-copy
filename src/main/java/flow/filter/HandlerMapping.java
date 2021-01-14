package flow.filter;

import flow.MainApplication;
import flow.annotation.Controller;
import flow.annotation.GetMapping;
import flow.annotation.PostMapping;
import flow.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {

    Map<String, MethodInvoker> controller = new HashMap<>();
    Map<String, MethodInvoker> restController = new HashMap<>();

    public void init(){
        subDirList(MainApplication.class.getResource(".").getPath());
        System.out.println("111");
    }

    private void subDirList(String source) {
        File dir = new File(source);
        File[] fileList = dir.listFiles();

        if (fileList != null) {
            try {
                for (int i = 0; i < fileList.length; i++) {
                    File file = fileList[i];
                    if (file.isFile()) {
                        String path = file.getPath();

                        if (path.endsWith(".class")) {
                            int classes = path.lastIndexOf("classes");
                            String substring = path.substring(classes + 8);
                            String className = substring.split(".class")[0].replace("\\", ".");
                            Class<?> aClass = Class.forName(className);
                            if (aClass.isAnnotationPresent(Controller.class)) {
                                Method[] methods = aClass.getMethods();
                                for (Method m : methods) {
                                    if (m.isAnnotationPresent(PostMapping.class)) {
                                        PostMapping declaredAnnotation = m.getDeclaredAnnotation(PostMapping.class);
                                        String value = declaredAnnotation.value();
                                        MethodInvoker methodInvoker = new MethodInvoker(value, MethodType.PAGE, aClass, m);
                                        controller.put(value, methodInvoker);
                                    } else if (m.isAnnotationPresent(GetMapping.class)) {
                                        GetMapping declaredAnnotation = m.getDeclaredAnnotation(GetMapping.class);
                                        String value = declaredAnnotation.value();
                                        MethodInvoker methodInvoker = new MethodInvoker(value, MethodType.PAGE, aClass, m);
                                        controller.put(value, methodInvoker);
                                    }
                                }
                            } else if (aClass.isAnnotationPresent(RestController.class)) {
                                Method[] methods = aClass.getMethods();
                                for (Method m : methods) {
                                    if (m.isAnnotationPresent(PostMapping.class)) {
                                        PostMapping declaredAnnotation = m.getDeclaredAnnotation(PostMapping.class);
                                        String value = declaredAnnotation.value();
                                        MethodInvoker methodInvoker = new MethodInvoker(value, MethodType.POST, aClass, m);
                                        restController.put(value, methodInvoker);
                                    } else if (m.isAnnotationPresent(GetMapping.class)) {
                                        GetMapping declaredAnnotation = m.getDeclaredAnnotation(GetMapping.class);
                                        String value = declaredAnnotation.value();
                                        MethodInvoker methodInvoker = new MethodInvoker(value, MethodType.GET, aClass, m);
                                        restController.put(value, methodInvoker);
                                    }
                                }
                            }
                        }
                    } else if (file.isDirectory()) {
                        subDirList(file.getCanonicalPath());
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
