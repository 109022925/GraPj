package com.cc.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.dao.UserDao;
import com.cc.pojo.User;
import com.cc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getByUsername(String username) {
        // 创建查询条件
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);

        return userDao.selectByMap(map);
    }

    @Override
    public boolean saveUser(User user) {
        user.setRole(User.Role.user);
        return userDao.insert(user) > 0;
    }

    @Override
    public boolean modify(User user) {
        return userDao.updateById(user) > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        return userDao.deleteById(id) > 0;
    }

}
