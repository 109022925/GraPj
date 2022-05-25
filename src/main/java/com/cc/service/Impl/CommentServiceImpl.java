package com.cc.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.dao.CommentDao;
import com.cc.dao.CommentVoDao;
import com.cc.pojo.Comment;
import com.cc.pojo.vo.CommentVo;
import com.cc.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentDao, Comment> implements ICommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private CommentVoDao commentVoDao;

    @Override
    public boolean saveComment(Comment comment) {
        return commentDao.insert(comment) > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        return commentDao.deleteById(id) > 0;
    }

    @Override
    public List<CommentVo> getAllCommentVo() {
        return commentVoDao.getAllCommentVo();
    }

    @Override
    public IPage<CommentVo> getPageByUid(int currentPage, int pageSize, Integer id) {
        IPage page = new Page(currentPage, pageSize);
        commentVoDao.getByUid(page, id);
        return page;
    }

    @Override
    public IPage<CommentVo> getPageByBid(int currentPage, int pageSize, Integer id) {
        IPage page = new Page(currentPage, pageSize);
        commentVoDao.getByBid(page, id);
        return page;
    }
}
