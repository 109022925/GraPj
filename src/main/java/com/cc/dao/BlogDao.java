package com.cc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.pojo.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogDao extends BaseMapper<Blog> {

    @Select("select bid, userid, title, description, content, time from blog join user where userid = uid and username = #{username}")
    List<Blog> getBlogByUsername(@Param("username") String name);
}
