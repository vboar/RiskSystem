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
    public Map<String, Object> login(@RequestBody Map reqMap, HttpSession session) {
        Map<String, Object> respMap = userService.login(reqMap);
        Map<String, Object> data = (Map)respMap.get("data");
        session.setAttribute("id", data.get("id"));
        session.setAttribute("username", data.get("username"));
        session.setAttribute("role", data.get("role"));
        return respMap;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(@RequestBody Map reqMap, HttpSession session) {
        Map<String, Object> respMap = userService.register(reqMap);
        Map<String, Object> data = (Map)respMap.get("data");
        session.setAttribute("id", data.get("id"));
        session.setAttribute("username", data.get("username"));
        session.setAttribute("role", data.get("role"));
        return respMap;
    }

    @RequestMapping(value="/logout", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> logout(HttpSession session) {
        Enumeration<String> em = session.getAttributeNames();
        while (em.hasMoreElements()) {
            session.removeAttribute(em.nextElement());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        return map;
    }

}
