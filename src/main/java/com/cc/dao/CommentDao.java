package com.cc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentDao extends BaseMapper<Comment> {
}
