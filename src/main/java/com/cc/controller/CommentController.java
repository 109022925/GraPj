package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.pojo.Comment;
import com.cc.pojo.User;
import com.cc.pojo.vo.CommentVo;
import com.cc.service.Impl.CommentServiceImpl;
import com.cc.service.Impl.UserServiceImpl;
import com.cc.utils.JwtUtil;
import com.cc.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @Auther: cc
 * @Date: 2022/3/15
 * @Description: 处理评论相关逻辑
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    @Autowired
    private UserServiceImpl userService;

    /**
     * @Auther: cc
     * @Date: 2022/3/16
     * @Param: null
     * @Return:
     * @Description: 管理员获取所有评论对象
     */
    @GetMapping()
    @RequiresRoles("admin")
    public Result getAllComment() {
        return Result.success(commentService.getAllCommentVo());
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return:
     * @Description: 根据uid获取评论分页对象
     */
    @PostMapping("/selectUid")
    @RequiresRoles("user")
    public Result getPageByUid(@RequestBody Map<String, Integer> map) {
        Integer uid = map.get("uid");
        Integer currentPage = map.get("currentPage");
        Integer pageSize = map.get("pageSize");
        IPage<CommentVo> c = commentService.getPageByUid(currentPage, pageSize, uid);
        return Result.success(c);
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return:
     * @Description: 根据bid获取评论分页对象
     */
    @PostMapping("/selectBid")
    @RequiresRoles("user")
    public Result getPageByBid(@RequestBody Map<String, Integer> map) {
        Integer bid = map.get("bid");
        Integer currentPage = map.get("currentPage");
        Integer pageSize = map.get("pageSize");
        IPage<CommentVo> c = commentService.getPageByBid(currentPage, pageSize, bid);
        return Result.success(c);
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return:
     * @Description: 发布评论
     */
    @PostMapping
    @RequiresRoles("user")
    public Result add(@RequestBody Comment comment) {
        Subject subject = SecurityUtils.getSubject();
        String username = JwtUtil.getUsernameFromToken(
                (String) subject.getPrincipal());

        User user = userService.getByUsername(username).get(0);
        comment.setUid(user.getUid());

        Date currentTime = new java.sql.Date(new Date().getTime());
        comment.setStamp(currentTime);

        if (commentService.saveComment(comment)) {
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return:
     * @Description: 根据id删除评论
     */
    @DeleteMapping("/{id}")
    @RequiresRoles(value = {"admin", "user"}, logical = Logical.OR)
    public Result deleteById(@PathVariable Integer id) {
        if (commentService.deleteById(id)) {
            return Result.success();
        }
        return Result.fail();
    }
}
