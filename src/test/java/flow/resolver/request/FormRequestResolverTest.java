package flow.resolver.request;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import flow.controller.RestTestController;
import flow.dto.TestDto1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

class FormRequestResolverTest {

    RequestResolver resolver = new FormRequestResolver();

    ObjectMapper objectMapper;
    {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    }

    @Test
    @DisplayName("Request_파라미터_세팅_테스트")
    public void Request_파라미터_세팅_테스트() throws Exception{
        HttpServletRequest  request = createMock(HttpServletRequest.class);

        expect(request.getParameter("name")).andReturn("name").times(1, 2);
        expect(request.getParameter("password")).andReturn("password").times(1, 2);
        replay(request);

        assertEquals("name", request.getParameter("name"));
        assertEquals("password", request.getParameter("password"));
    }

    @Test
    @DisplayName("Form_데이터_변환_실패_테스트")
    public void Form_데이터_변환_실패_테스트() throws Exception{
        assertThrows(InvalidFormatException.class, () -> {
            HttpServletRequest  request = createMock(HttpServletRequest.class);
            List<String> parameterNames = new ArrayList<>();
            parameterNames.add("name");
            parameterNames.add("password");

            expect(request.getParameter("name")).andReturn("name").times(1, 2);
            expect(request.getParameter("password")).andReturn("abc").times(1, 2);
            expect(request.getParameterNames()).andReturn(Collections.enumeration(parameterNames));

            replay(request);

            Method method = RestTestController.class.getMethod("restPostMapping", TestDto1.class);
            final Object[] parameter = resolver.getParameter(request, method);
        });
    }

    @Test
    @DisplayName("Form_데이터_변환_성공_테스트")
    public void Form_데이터_변환_성공_테스트() throws Exception{
        HttpServletRequest  request = createMock(HttpServletRequest.class);
        List<String> parameterNames = new ArrayList<>();
        parameterNames.add("name");
        parameterNames.add("password");

        expect(request.getParameter("name")).andReturn("name").times(1, 2);
        expect(request.getParameter("password")).andReturn("123456").times(1, 2);
        expect(request.getParameterNames()).andReturn(Collections.enumeration(parameterNames));

        replay(request);

        Method method = RestTestController.class.getMethod("restPostMapping", TestDto1.class);
        final Object[] parameter = resolver.getParameter(request, method);

        assertNotNull(parameter);
        assertEquals(TestDto1.class, parameter[0].getClass());
        TestDto1 result = (TestDto1) parameter[0];
        assertEquals("name", result.getName());
        assertEquals(123456, result.getPassword());
    }

}