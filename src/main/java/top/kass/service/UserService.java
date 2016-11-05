package top.kass.service;

import java.util.Map;

public interface UserService {

    public Map<String, Object> login(Map reqMap);

    public Map<String, Object> register(Map reqMap);

}
