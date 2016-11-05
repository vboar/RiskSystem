package top.kass.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @RequestMapping(value="/getMyInfo", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getMyInfo() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @RequestMapping(value="/password", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> password() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

}
