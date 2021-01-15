package flow.controller;

import flow.annotation.GetMapping;
import flow.annotation.PostMapping;
import flow.annotation.RestController;

@RestController
public class RestTestController {

    @PostMapping("/rest/posttest")
    public String restPostMapping(){
        return "post";
    }

    @GetMapping("/rest/gettest")
    public String restGetMapping(){
        return "get";
    }

}
