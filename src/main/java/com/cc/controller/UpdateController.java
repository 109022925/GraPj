package com.cc.controller;

import com.cc.pojo.User;
import com.cc.service.Impl.UserServiceImpl;
import com.cc.service.Impl.mail.MailServiceImpl;
import com.cc.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Auther: cc
 * @Date: 2022/3/15
 * @Description: 处理修改密码相关逻辑
 */
@RestController
@RequestMapping("/update")
public class UpdateController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MailServiceImpl mailService;

    /**
     * @Auther: cc
     * @Date: 2022/3/15
      * @PathVariable username: String
      * @Param session: HttpSession
     * @Return:
     * @Description: 确认用户名并发送验证码
     */
    @GetMapping("/input/{username}")
    public Result input(@PathVariable String username, HttpSession session) {
        List<User> u = userService.getByUsername(username);
        if (u.isEmpty()) {
            return Result.fail(403, "用户名不存在");
        } else {
            String email = u.get(0).getEmail();
            if (mailService.send(email, session)) {
                session.setAttribute("username", username);
                return Result.success(email);
            }
        }
        return Result.fail("验证码发送失败");
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @PathVariable code: String
     * @Param session: HttpSession
     * @Return:
     * @Description: 确认发送的验证码
     */
    @GetMapping("/verify/{code}")
    public Result verify(@PathVariable String code, HttpSession session) {
        if (mailService.verify(code, session)) {
            return Result.success();
        }
        return Result.fail(401, "验证码错误");
    }

    // 用户更改密码
    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @RequestBody password: String
     * @Param session: HttpSession
     * @Return:
     * @Description: 用户更改密码
     */
    @PutMapping()
    public Result updatePsw(@RequestBody Map<String, String> map, HttpSession session) {

        String username = (String) session.getAttribute("username");
        List<User> u = userService.getByUsername(username);
        User newUser = u.get(0);
        newUser.setPassword(map.get("password"));
        if (userService.modify(newUser)) {
            return Result.success();
        }
        return Result.fail(401, "修改失败");
    }
}
