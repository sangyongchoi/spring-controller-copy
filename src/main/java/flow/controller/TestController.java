package flow.controller;

import flow.annotation.Controller;
import flow.annotation.GetMapping;

@Controller
public class TestController {
    public TestController() {
    }

    @GetMapping("/controllergettest")
    public String controllerGetMapping(){
        return "controller get";
    }

    @GetMapping("/controllerposttest")
    public String controllerPostMapping(){
        return "controller post";
    }
}
