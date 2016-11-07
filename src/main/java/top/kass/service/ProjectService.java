package top.kass.service;

import java.util.Map;

public interface ProjectService {

    public Map<String, Object> add(Map reqMap, int id);

    public Map<String, Object> getMyProjects(int id);

    public Map<String, Object> delete(int id, int uid);

    public Map<String, Object> modify(Map reqMap, int id);

    public Map<String, Object> getById(int id, int uid);

}
