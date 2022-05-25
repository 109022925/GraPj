package com.cc.controller;

import com.cc.pojo.User;
import com.cc.service.Impl.UserServiceImpl;
import com.cc.utils.JwtToken;
import com.cc.utils.JwtUtil;
import com.cc.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Auther: cc
 * @Date: 2022/3/15
 * @Description: 处理登录、登出相关逻辑
 */
@RestController
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param user: User
     * @Return: {
     * code：
     * 401：用户名不存在或密码错误
     * 200：登录成功
     * data：
     * token：权限口令
     * uid：在表中对应的id
     * username：用户名
     * role：用户角色
     * }
     * @Description: 登录接口
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();

        List<User> u = userService.getByUsername(user.getUsername());
        if (u.isEmpty()) {
            return new Result(401, "用户名不存在");
        }

        User newUser = u.get(0);
        String token = JwtUtil.createToken(newUser.getUsername());
        HashMap<String, Object> result = new HashMap<>();

        if (!newUser.getPassword().equals(user.getPassword())) {
            // 密码错误
            return new Result(401, "密码错误");
        }

        JwtToken jwt = new JwtToken(token);
        subject.login(jwt);

        result.put("uid", newUser.getUid());
        result.put("username", newUser.getUsername());
        result.put("role", newUser.getRole());
        result.put("token", token);

        return Result.success(result);
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return: {
     * message:
     * 退出登录
     * }
     * @Description: 登出接口
     */
    @GetMapping("/logout")
    @RequiresRoles("user")
    public Result logout() {
        //在这里执行退出系统前需要清空的数据
        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated()) {
            subject.logout();
        }

        return Result.success("退出登录");
    }
}
