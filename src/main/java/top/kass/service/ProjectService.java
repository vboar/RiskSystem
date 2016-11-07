package top.kass.service;

import java.util.Map;

public interface ProjectService {

    public Map<String, Object> add(Map reqMap, int id);

    public Map<String, Object> getMyProjects(int id);

}
