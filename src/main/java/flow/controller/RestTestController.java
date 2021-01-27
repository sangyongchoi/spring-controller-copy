package flow.controller;

import flow.annotation.GetMapping;
import flow.annotation.PostMapping;
import flow.annotation.RestController;
import flow.dto.TestDto;

@RestController
public class RestTestController {

    @PostMapping("/rest/posttest")
    public String restPostMapping(TestDto test){
        System.out.println(test);
        return "post";
    }

    @GetMapping("/rest/gettest")
    public String restGetMapping(){
        return "get";
    }

}
