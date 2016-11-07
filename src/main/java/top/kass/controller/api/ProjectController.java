package top.kass.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.kass.service.ProjectService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // 我的项目，分为我创建的和我参与的
    @RequestMapping(value="/getMyProjects", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getMyProjects(HttpSession session) {
        return projectService.getMyProjects((int)session.getAttribute("id"));
    }

    @RequestMapping(value="/getById", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getById() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(@RequestBody Map reqMap, HttpSession session) {
        return projectService.add(reqMap, (int)session.getAttribute("id"));
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

    // 获取本项目中的所有成员（创建者+参与者）
    @RequestMapping(value="/getUsersByPid", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUsersByPid() {
        Map<String, Object> map = new HashMap<>();
        return map;
    }

}
