package flow.resolver.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import static org.easymock.EasyMock.*;
import static org.junit.jupiter.api.Assertions.*;

class RequestResolverFactoryTest {

    @Test
    @DisplayName("RequestResolverFactory Get Resolver Test")
    public void RequestResolverFoctory_Get_Resolver_Test_When_Application_Json() throws Exception{
        HttpServletRequest request = createMock(HttpServletRequest.class);
        expect(request.getContentType()).andReturn("application/json");
        replay(request);

        RequestResolver requestResolver = RequestResolverFactory.getResolver(request);

        assertEquals(JsonRequestResolver.class, requestResolver.getClass());
    }

    @Test
    @DisplayName("RequestResolverFactory Get Resolver Test")
    public void RequestResolverFoctory_Get_Resolver_Test_When_Form() throws Exception{
        HttpServletRequest request = createMock(HttpServletRequest.class);
        expect(request.getContentType()).andReturn("application/x-www-form-urlencoded");
        replay(request);

        RequestResolver requestResolver = RequestResolverFactory.getResolver(request);

        assertEquals(FormRequestResolver.class, requestResolver.getClass());
    }

    @Test
    @DisplayName("RequestResolverFactory Get Resolver Test")
    public void RequestResolverFoctory_Get_Resolver_Test_When_Default() throws Exception{
        HttpServletRequest request = createMock(HttpServletRequest.class);
        RequestResolver requestResolver = RequestResolverFactory.getResolver(request);

        assertEquals(DefaultRequestResolver.class, requestResolver.getClass());
    }

}