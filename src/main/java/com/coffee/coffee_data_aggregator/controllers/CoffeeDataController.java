package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class CoffeeDataController {
    @RequestMapping("/")
    public String start(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String start(){
        return "index";
    }
}
