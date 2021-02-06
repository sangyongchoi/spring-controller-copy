package flow.filter.handler;

import flow.controller.RestTestController;
import flow.exception.HandlerCreateException;
import flow.exception.NotFoundException;
import flow.fail.FailTestController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HandlerMappingTest {

    @Test
    @DisplayName("Handdler 등록 테스트")
    public void register_handler() throws Exception{
        HandlerMapping handlerMapping = new HandlerMapping();
        handlerMapping.init(RestTestController.class);

        assertNotNull(handlerMapping.getHandler("/rest/posttest"));
        assertNotNull(handlerMapping.getHandler("/rest/gettest"));
        assertNotNull(handlerMapping.getHandler("/index"));
        assertNotNull(handlerMapping.getHandler("/test"));
    }
    
    @Test
    @DisplayName("Handler Not Found")
    public void notfound_handler() throws Exception{
        assertThrows(NotFoundException.class, () -> {
            HandlerMapping handlerMapping = new HandlerMapping();
            handlerMapping.init(RestTestController.class);

            handlerMapping.getHandler("/notfound");
        });
    }

    @Test
    @DisplayName("Duplication Handler URI")
    public void fail_when_duplicate_handler_uri(){
        assertThrows(HandlerCreateException.class, () -> {
            HandlerMapping handlerMapping = new HandlerMapping();
            handlerMapping.init(FailTestController.class);
        });
    }

}