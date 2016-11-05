package top.kass.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/riskState")
public class RiskStateController {

    @RequestMapping(value="/getRiskStatesByRid", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRiskStatesByRid() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @RequestMapping(value="/getById", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getById() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @RequestMapping(value="/modify", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modify() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @RequestMapping(value="/delete", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> delete() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

}
