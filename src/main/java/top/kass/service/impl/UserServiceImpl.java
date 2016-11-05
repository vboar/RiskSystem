package top.kass.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.kass.dao.UserDao;
import top.kass.service.UserService;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", userDao.getByUsername(username));
        return map;
    }

}
