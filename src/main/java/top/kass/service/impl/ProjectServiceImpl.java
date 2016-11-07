package top.kass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kass.dao.ProjectDao;
import top.kass.model.Project;
import top.kass.service.ProjectService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public Map<String, Object> add(Map reqMap, int id) {

        Map<String, Object> map = new HashMap<>();

        String name = (String)reqMap.get("name");
        String description = (String)reqMap.get("description");
        List<String> uids = (List<String>)reqMap.get("users");

        if (name.length() == 0) {
            map.put("code", 100);
            map.put("msg", "项目名称不能为空");
            return map;
        }

        Project project = new Project();
        project.setName(name);
        project.setDescription(description);
        project.setCreator(id);
        project.setCreateTime(new Timestamp(System.currentTimeMillis()));
        project = projectDao.create(project);
        projectDao.updateUsers(project.getId(), uids, id);

        map.put("code", 0);
        Map<String, Object> data = new HashMap<>();
        data.put("id", project.getId());
        data.put("name", project.getName());
        data.put("description", project.getDescription());
        data.put("creator", project.getCreator());
        data.put("createTime", project.getCreateTime());
        map.put("data", data);
        return map;
    }

    @Override
    public Map<String, Object> getMyProjects(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        Map<String, Object> data = new HashMap<>();
        data.put("create", projectDao.getCreatedProjectsByUid(id));

        List<Map> ins = projectDao.getProjectsByUid(id);
        List<Map> newIns = new ArrayList<>();
        for (Map m: ins) {
            if ((int)m.get("creator") != id) {
                newIns.add(m);
            }
        }

        data.put("in", newIns);
        map.put("data", data);
        return map;
    }

}
