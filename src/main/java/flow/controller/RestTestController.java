package flow.controller;

import flow.annotation.GetMapping;
import flow.annotation.PostMapping;
import flow.annotation.RestController;

@RestController
public class RestTestController {

    @PostMapping("/restpostmapping")
    public String restPostMapping(){
        return "post";
    }

    @GetMapping("/restgetmapping")
    public String restGetMapping(){
        return "get";
    }

}
