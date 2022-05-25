package com.cc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cc.pojo.Blog;
import com.cc.pojo.Favor;
import com.cc.service.Impl.BlogServiceImpl;
import com.cc.service.Impl.FavorServiceImpl;
import com.cc.utils.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: cc
 * @Date: 2022/3/15
 * @Description: 处理用户收藏相关逻辑
 */
@RestController
@RequestMapping("/user/favor")
public class FavorController {
    @Autowired
    private FavorServiceImpl favorService;

    @Autowired
    private BlogServiceImpl blogService;

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return:
     * @Description: 获取当前用户收藏博文分页对象
     */
    @PostMapping()
    @RequiresRoles("user")
    public Result getFavorBlogByUid(@RequestBody Map<String, Integer> map) {
        Integer uid = map.get("uid");
        Integer currentPage = map.get("currentPage");
        Integer pageSize = map.get("pageSize");

        List<Favor> favors = favorService.getByUid(uid);
        ArrayList<Integer> bid = new ArrayList<>();
        for (Favor f : favors
        ) {
            bid.add(f.getBid());
        }

        // 批量查询
        List<Blog> blogs = blogService.getBatchIds(bid);
        // 构造分页对象
        IPage page = new Page(currentPage, pageSize);
        page.setRecords(blogs);
        page.setTotal(blogs.size());

        return Result.success(page);
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return:
     * @Description: 获取当前blog是否被用户收藏
     */
    @GetMapping()
    @RequiresRoles("user")
    public Result verifyStar(@RequestParam Integer bid, @RequestParam Integer uid) {
        if (favorService.verifyStar(bid, uid)) {
            return Result.success(true);
        } else {
            return Result.success(false);
        }
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return:
     * @Description: 收藏blog
     */
    @PostMapping("/add")
    @RequiresRoles("user")
    public Result addFavor(@RequestBody Favor favor) {
        if (favorService.save(favor)) {
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return:
     * @Description: 取消收藏blog
     */
    @PostMapping("/cancel")
    @RequiresRoles("user")
    public Result cancelStar(@RequestBody Favor favor) {
        if (favorService.deleteByUidBid(favor)) {
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/16
     * @Param: null
     * @Return:
     * @Description: 管理员获取所有收藏vo对象
     */
    @GetMapping("/admin")
    @RequiresRoles("admin")
    public Result getAllFavorVo() {
        return Result.success(favorService.getAllFavorVo());
    }
}
