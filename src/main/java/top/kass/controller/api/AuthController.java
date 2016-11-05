package top.kass.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @RequestMapping(value="/login", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @RequestMapping(value="/logout", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> logout() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

}
