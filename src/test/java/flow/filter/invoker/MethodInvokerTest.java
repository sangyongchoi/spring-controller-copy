package flow.filter.invoker;

import com.fasterxml.jackson.core.JsonProcessingException;
import flow.controller.RestTestController;
import flow.dto.TestDto1;
import flow.exception.MethodInvokeException;
import flow.filter.handler.RequestType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static flow.common.CommonUtil.objectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MethodInvokerTest {

    @Test
    @DisplayName("메소드 실행 테스트")
    public void method_invoke_success_get_test() throws Exception{
        Method m = RestTestController.class.getMethod("restGetMapping");
        MethodInvoker invoker = new MethodInvoker(RestTestController.class, m, RequestType.GET);
        final Object invoke = invoker.invoke(new Object[0]);

        assertEquals("get", invoke.toString());
    }

    @Test
    @DisplayName("메소드 실행 테스트")
    public void method_invoke_success_post_test() throws Exception{
        Method m = RestTestController.class.getMethod("restPostMapping", TestDto1.class);
        MethodInvoker invoker = new MethodInvoker(RestTestController.class, m, RequestType.POST);
        TestDto1 testDto = getParameter();
        final Object invoke = invoker.invoke(new Object[]{testDto});

        assertEquals("post", invoke.toString());
    }

    @Test
    @DisplayName("메소드 실행 실패 테스트 - Exception")
    public void method_invoke_fail_exception_test() throws Exception{
        assertThrows(MethodInvokeException.class, () -> {
            Method m = RestTestController.class.getMethod("failTestMethod");
            MethodInvoker invoker = new MethodInvoker(RestTestController.class, m, RequestType.POST);
            final Object invoke = invoker.invoke(new Object[0]);
        });
    }

    @Test
    @DisplayName("메소드 실행 테스트_파라미터_누락")
    public void method_invoke_fail_post_test() throws Exception{
        assertThrows(IllegalArgumentException.class, () -> {
            Method m = RestTestController.class.getMethod("restPostMapping", TestDto1.class);
            MethodInvoker invoker = new MethodInvoker(RestTestController.class, m, RequestType.POST);
            final Object invoke = invoker.invoke(new Object[0]);
        });
    }

    TestDto1 getParameter() throws JsonProcessingException {
        String json = "{\n" +
                "    \"name\" : \"test\",\n" +
                "    \"password\":\"123\"\n" +
                "}";

        return objectMapper.readValue(json, TestDto1.class);
    }

}