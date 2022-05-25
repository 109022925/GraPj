package com.cc.controller;

import com.cc.pojo.User;
import com.cc.service.Impl.UserServiceImpl;
import com.cc.service.Impl.mail.MailServiceImpl;
import com.cc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Auther: cc
 * @Date: 2022/3/15
 * @Description: 处理注册相关逻辑
 */
@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MailServiceImpl mailService;

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @PathVariable email: String
     * @Param session: HttpSession
     * @Return:
     * @Description: 向注册邮箱发送验证码
     */
    @GetMapping("/{email}")
    public Result send(@PathVariable String email, HttpSession session) {
        if (mailService.send(email, session)) {
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @PathVariable code: String
     * @Param user: User
     * @Return:
     * @Description: 验证验证码并注册
     */
    @PostMapping("/{code}")
    public Result verify(@PathVariable String code, @RequestBody User user, HttpSession session) {
        if (mailService.verify(code, session)) {
            List<User> u = userService.getByUsername(user.getUsername());

            if (u.isEmpty()) {
                user.setRole(User.Role.user);
                if (userService.saveUser(user)) {
                    return Result.success();
                }
            } else return Result.fail(403, "用户名重复");
        } else return Result.fail(401, "验证码错误");
        return Result.fail("服务器错误");
    }
}
