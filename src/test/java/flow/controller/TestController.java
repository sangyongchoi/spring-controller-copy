package flow.controller;

import flow.annotation.Controller;
import flow.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/index")
    public String controllerGetMapping(){
        return "/index.jsp";
    }

    @GetMapping("/test")
    public String controllerPostMapping(){
        return "/test.jsp";
    }

}
