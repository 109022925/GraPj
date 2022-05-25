package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.pojo.Blog;
import com.cc.pojo.vo.BlogVo;
import com.cc.service.Impl.BlogServiceImpl;
import com.cc.service.Impl.UserServiceImpl;
import com.cc.utils.Result;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: cc
 * @Date: 2022/3/15
 * @Description: 处理博文相关逻辑
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private UserServiceImpl userService;

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return:
     * @Description: 获取分页blogvo
     */
    @GetMapping()
    @RequiresRoles(value = {"admin", "user"}, logical = Logical.OR)
    public Result getPage(
            @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize
    ) {
        IPage<BlogVo> blogs = blogService.getPage(currentPage, pageSize);
        return Result.success(blogs);
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return:
     * @Description: 根据uid获取分页blog
     */
    @PostMapping("/selectUid")
    @RequiresRoles("user")
    public Result getBlogByUid(@RequestBody Map<String, Integer> map) {
        Integer uid = map.get("uid");
        Integer currentPage = map.get("currentPage");
        Integer pageSize = map.get("pageSize");
        IPage<Blog> blogs = blogService.getPageByUid(currentPage, pageSize, uid);
        return Result.success(blogs);
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return:
     * @Description: 添加博文
     */
    @PostMapping
    @RequiresRoles("user")
    public Result add(@RequestBody Blog blog) {

        Date date = new Date();
        SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //这是24时
        String time = temp.format(date);
        blog.setTime(time);

        if (blogService.saveBlog(blog)) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return:
     * @Description: 根据bid删除博文
     */
    @DeleteMapping("{id}")
    @RequiresRoles(value = {"admin", "user"}, logical = Logical.OR)
    public Result delete(@PathVariable Integer id) {
        boolean flag = blogService.deleteById(id);
        if (flag) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return:
     * @Description: 根据bid获取博文对象和博文作者
     */
    @GetMapping("/select/{id}")
    @RequiresRoles(value = {"user"})
    public Result getById(@PathVariable Integer id) {
        Blog blog = blogService.getById(id);
        String username = userService.getById(blog.getUid()).getUsername();

        Map<String, Object> map = new HashMap<>();
        map.put("blog", blog);
        map.put("author", username);
        return Result.success(map);
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return:
     * @Description: 修改博文
     */
    @PutMapping
    @RequiresRoles("user")
    public Result update(@RequestBody Blog blog) {
        Date date = new Date();
        SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //这是24时
        String time = temp.format(date);
        blog.setTime(time);

        if (blogService.modify(blog)) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/16
     * @Param: null
     * @Return:
     * @Description: admin获取所有博文对象
     */
    @GetMapping("/select")
    @RequiresRoles("admin")
    public Result getAll() {
        return Result.success(blogService.getAllBlogVo());
    }
}
