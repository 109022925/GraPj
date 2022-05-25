package com.cc.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.dao.BlogDao;
import com.cc.dao.BlogVoDao;
import com.cc.pojo.Blog;
import com.cc.pojo.vo.BlogVo;
import com.cc.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogDao, Blog> implements IBlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private BlogVoDao blogVoDao;

    @Override
    public boolean saveBlog(Blog blog) {
        return blogDao.insert(blog) > 0;
    }

    @Override
    public boolean modify(Blog blog) {
        return blogDao.updateById(blog) > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        return blogDao.deleteById(id) > 0;
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/16
     * @Param: null
     * @Return:
     * @Description: 获取所有blogvo对象
     */
    @Override
    public List<BlogVo> getAllBlogVo() {
        return blogVoDao.getAllBlogVo();
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return:
     * @Description: 获取分页blogvo
     */
    @Override
    public IPage<BlogVo> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage, pageSize);
        blogVoDao.getPage(page);
        return page;
    }

    /**
     * @Auther: cc
     * @Date: 2022/3/15
     * @Param: null
     * @Return:
     * @Description: 根据uid获取分页blog
     */
    @Override
    public IPage<Blog> getPageByUid(int currentPage, int pageSize, Integer id) {

        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", id).orderByDesc("time");

        IPage page = new Page(currentPage, pageSize);
        blogDao.selectPage(page, queryWrapper);

        return page;
    }

    @Override
    public List<Blog> getBatchIds(ArrayList<Integer> ids) {
        return blogDao.selectBatchIds(ids);
    }
}
