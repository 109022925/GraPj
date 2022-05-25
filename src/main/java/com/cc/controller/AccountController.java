package com.cc.controller;

import com.cc.pojo.User;
import com.cc.service.Impl.UserServiceImpl;
import com.cc.utils.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * @Auther: cc
     * @Date: 2022/3/16
     * @Param: null
     * @Return:
     * @Description: 管理员获取所有账号信息
     */
    @GetMapping
    @RequiresRoles("admin")
    public Result getAll() {
        return Result.success(userService.list());
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/16
     * @Param: null
     * @Return:
     * @Description: 管理员根据uid更改密码
     */
    @PutMapping("/psw")
    @RequiresRoles("admin")
    public Result updatePsw(@RequestBody Map<String, Object> map) {
        Integer uid = (Integer) map.get("uid");
        String psw = (String) map.get("password");

        User user = userService.getById(uid);
        user.setPassword(psw);
        if (userService.modify(user)) {
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/16
     * @Param: null
     * @Return:
     * @Description: 管理员根据uid更改邮箱
     */
    @PutMapping("/email")
    @RequiresRoles("admin")
    public Result updateEmail(@RequestBody Map<String, Object> map) {
        Integer uid = (Integer) map.get("uid");
        String email = (String) map.get("email");

        User user = userService.getById(uid);
        user.setEmail(email);
        if (userService.modify(user)) {
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/16
     * @Param: null
     * @Return:
     * @Description: 管理员删除账号
     */
    @DeleteMapping("/{id}")
    @RequiresRoles("admin")
    public Result delete(@PathVariable Integer id) {
        if (userService.deleteById(id)) return Result.success();
        return Result.fail();
    }
}
