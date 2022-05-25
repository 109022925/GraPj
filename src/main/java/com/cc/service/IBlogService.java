package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.pojo.Blog;
import com.cc.pojo.vo.BlogVo;

import java.util.ArrayList;
import java.util.List;

public interface IBlogService {
    boolean saveBlog(Blog blog);

    boolean modify(Blog blog);

    boolean deleteById(Integer id);

    List<BlogVo> getAllBlogVo();

    IPage<BlogVo> getPage(int currentPage, int pageSize);

    IPage<Blog> getPageByUid(int currentPage, int pageSize, Integer id);

    List<Blog> getBatchIds(ArrayList<Integer> ids);
}
