package flow.controller;

import flow.annotation.GetMapping;
import flow.annotation.PostMapping;
import flow.dto.TestDto1;

public class RestTestController {
    @PostMapping("/rest/posttest")
    public String restPostMapping(TestDto1 test){
        System.out.println(test.toString());
        return "post";
    }

    @GetMapping("/rest/gettest")
    public String restGetMapping(){
        return "get";
    }
}
