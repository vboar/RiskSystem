package top.kass.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value="/info", method= RequestMethod.GET)
    public String info() {
        return "info";
    }

    @RequestMapping(value="/project", method= RequestMethod.GET)
    public String project() {
        return "project";
    }

    @RequestMapping(value="/risk", method= RequestMethod.GET)
    public String risk() {
        return "risk";
    }

}
