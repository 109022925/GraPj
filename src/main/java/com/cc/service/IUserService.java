package com.cc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cc.pojo.User;

import java.util.List;


public interface IUserService extends IService<User> {

    boolean saveUser(User user);

    boolean modify(User user);

    boolean deleteById(Integer id);

    List<User> getByUsername(String username);
}
