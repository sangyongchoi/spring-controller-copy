package flow.filter;

import flow.TestApplication;
import flow.filter.handler.HandlerMapping;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.management.ServiceNotFoundException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HandlerMappingTest {

    static HandlerMapping handlerMapping = new HandlerMapping();

    @BeforeAll
    public static void beforeAll(){
        handlerMapping.init(TestApplication.class);
    }

    @Test
    @DisplayName("Handdler 등록 테스트")
    public void register_handler() throws Exception{
        assertNotNull(handlerMapping.getHandler("/rest/posttest"));
        assertNotNull(handlerMapping.getHandler("/rest/gettest"));
        assertNotNull(handlerMapping.getHandler("/index"));
        assertNotNull(handlerMapping.getHandler("/test"));
    }
    
    @Test
    @DisplayName("Handler Not Found")
    public void notfound_handler() throws Exception{
        assertThrows(ServiceNotFoundException.class, () -> {
            handlerMapping.getHandler("/notfound");
        });
    }
}