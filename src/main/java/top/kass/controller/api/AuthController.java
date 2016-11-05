package top.kass.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.kass.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/login", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@RequestBody Map reqMap) {
        Map<String, Object> map = userService.login(reqMap);
        return map;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(@RequestBody Map reqMap) {
        Map<String, Object> respMap = new HashMap<>();
        return respMap;
    }

    @RequestMapping(value="/logout", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> logout(HttpSession session) {
        Enumeration<String> em = session.getAttributeNames();
        while (em.hasMoreElements()) {
            session.removeAttribute(em.nextElement().toString());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        return map;
    }

}
