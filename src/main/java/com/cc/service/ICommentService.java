package com.cc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.pojo.Comment;
import com.cc.pojo.vo.CommentVo;

import java.util.List;

public interface ICommentService {

    boolean saveComment(Comment comment);

    boolean deleteById(Integer id);

    List<CommentVo> getAllCommentVo();

    IPage<CommentVo> getPageByBid(int currentPage, int pageSize, Integer id);

    IPage<CommentVo> getPageByUid(int currentPage, int pageSize, Integer id);
}
