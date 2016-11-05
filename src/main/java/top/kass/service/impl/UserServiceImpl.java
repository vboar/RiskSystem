package top.kass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kass.dao.UserDao;
import top.kass.model.User;
import top.kass.service.UserService;
import top.kass.util.Util;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> login(Map reqMap) {
        Map<String, Object> map = new HashMap<>();
        String username = (String)reqMap.get("username");
        String password = (String)reqMap.get("password");
        User user = userDao.getByUsername(username);
        if (user == null || Util.md5(password).equals(user.getPassword())) {
            map.put("code", 100);
            map.put("msg", "用户名或密码错误");
        } else {
            map.put("code", 0);
            Map<String, Object> data = new HashMap<>();
            data.put("id", user.getId());
            data.put("username", user.getUsername());
            map.put("data", data);
        }
        return map;
    }

    @Override
    public Map<String, Object> register(Map reqMap) {

        return null;
    }

}
