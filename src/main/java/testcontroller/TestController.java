package testcontroller;

import org.springframework.stereotype.Controller;

@Controller
public class TestController {

    public TestController() {
        super();
        System.out.println("--TestController()--");
    }
}
