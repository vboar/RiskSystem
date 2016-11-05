package top.kass.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/risk")
public class RiskController {

    // 参数：key index num flag（所有、我创建的、我参与的）
    @RequestMapping(value="/getRisksByPid", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getRisksByPid() {
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
