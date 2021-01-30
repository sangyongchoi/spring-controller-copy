package flow.filter;

import flow.MainApplication;
import flow.filter.handler.HandlerMapping;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class HandlerMappingTest {

    @Test
    @DisplayName("Handdler 등록 테스트")
    public void register_handler() throws Exception{
        HandlerMapping handlerMapping = new HandlerMapping();
        handlerMapping.init(MainApplication.class);

        assertNotNull(handlerMapping.getHandler("/rest/posttest"));
        assertNotNull(handlerMapping.getHandler("/rest/gettest"));
        assertNotNull(handlerMapping.getHandler("/index"));
        assertNotNull(handlerMapping.getHandler("/test"));
    }
}