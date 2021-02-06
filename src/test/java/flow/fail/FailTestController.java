package flow.fail;

import flow.annotation.PostMapping;
import flow.annotation.RestController;

@RestController
public class FailTestController {

    @PostMapping("/failtest")
    public void method1(){
    }

    @PostMapping("/failtest")
    public void method2(){
    }

}
