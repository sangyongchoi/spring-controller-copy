package flow.resolver.request;

import flow.common.CommonUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public abstract class RequestResolver {
    public Object[] getParameter(HttpServletRequest request, Method method) throws IOException{
        Parameter[] parameters = method.getParameters();
        if (parameters.length == 0) {
            return new Object[0];
        }

        String inputData = getInputData(request);
        Object[] result = new Object[1];
        Parameter parameter = parameters[0];
        result[0] = CommonUtil.objectMapper.readValue(inputData, parameter.getType());

        return result;
    }

    protected abstract String getInputData(HttpServletRequest request) throws IOException;
}
