package com.cc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cc.dao.UserDao;
import com.cc.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Study {

    @Autowired
    private UserDao userDao;

    @Test
    void test() {
        LambdaQueryWrapper<User> lQueryWrapper = new LambdaQueryWrapper<User>();
        lQueryWrapper.likeLeft(User::getUsername, "n");
        List<User> users = userDao.selectList(lQueryWrapper);
        System.out.println(users);
    }

}
