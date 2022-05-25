package com.cc.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.pojo.vo.BlogVo;
import com.cc.pojo.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogVoDao {
    @Select("SELECT bid, username, title, description, time FROM blog JOIN user WHERE blog.uid = user.uid ORDER BY time DESC")
    IPage<BlogVo> getPage(IPage<CommentVo> page);

    @Select("SELECT bid, username, title, NULL AS description, time FROM blog JOIN user WHERE blog.uid = user.uid ORDER BY time DESC")
    List<BlogVo> getAllBlogVo();
}
