package flow.filter.invoker;

import com.fasterxml.jackson.core.JsonProcessingException;
import flow.controller.RestTestController;
import flow.dto.TestDto;
import flow.filter.handler.RequestType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static flow.common.CommonUtil.objectMapper;
import static org.junit.jupiter.api.Assertions.*;

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
        Method m = RestTestController.class.getMethod("restPostMapping", TestDto.class);
        MethodInvoker invoker = new MethodInvoker(RestTestController.class, m, RequestType.POST);
        TestDto testDto = getParameter();
        final Object invoke = invoker.invoke(new Object[]{testDto});

        assertEquals("post", invoke.toString());
    }

    @Test
    @DisplayName("메소드 실행 테스트_파라미터_누락")
    public void method_invoke_fail_post_test() throws Exception{
        assertThrows(IllegalArgumentException.class, () -> {
            Method m = RestTestController.class.getMethod("restPostMapping", TestDto.class);
            MethodInvoker invoker = new MethodInvoker(RestTestController.class, m, RequestType.POST);
            final Object invoke = invoker.invoke(new Object[0]);
        });
    }

    TestDto getParameter() throws JsonProcessingException {
        String json = "{\n" +
                "    \"name\" : \"test\",\n" +
                "    \"number\":\"123\",\n" +
                "    \"wrapperNumber\":\"1234\",\n" +
                "    \"list\" : [\n" +
                "        {\n" +
                "            \"test\":\"test\",\n" +
                "            \"title\":\"title\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"test\":\"test1\",\n" +
                "            \"title\":\"title2\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"map\" : {\n" +
                "        \"test\":\"123\",\n" +
                "        \"test1\":\"456\"\n" +
                "    }\n" +
                "}";

        return objectMapper.readValue(json, TestDto.class);
    }
}