package flow.controller;

import flow.annotation.Controller;
import flow.annotation.GetMapping;

@Controller
public class TestController {
    public TestController() {
    }

    @GetMapping
    public String getMapping(){
        return "";
    }
}
