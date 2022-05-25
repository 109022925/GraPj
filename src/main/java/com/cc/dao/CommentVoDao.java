package com.cc.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cc.pojo.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentVoDao {
    @Select(
            "SELECT cid, comment.content, stamp, title, username \n" +
                    "FROM `comment` JOIN `user` JOIN blog \n" +
                    "WHERE comment.uid = #{uid}\n" +
                    "AND comment.bid = blog.bid\n" +
                    "AND comment.uid = user.uid\n" +
                    "ORDER BY stamp DESC"
    )
    IPage<CommentVo> getByUid(IPage<CommentVo> page, @Param("uid") Integer uid);

    @Select(
            "SELECT cid, comment.content, stamp, title, username \n" +
                    "FROM `comment` JOIN `user` JOIN blog \n" +
                    "WHERE blog.bid = #{bid} \n" +
                    "AND comment.uid = user.uid \n" +
                    "AND comment.bid = blog.bid\n" +
                    "ORDER BY stamp DESC"
    )
    IPage<CommentVo> getByBid(IPage<CommentVo> page, @Param("bid") Integer bid);

    @Select(
            "SELECT cid, comment.content, stamp, title, username " +
                    "FROM `comment` JOIN `user` JOIN blog" +
                    " WHERE comment.bid = blog.bid " +
                    "AND comment.uid = user.uid" +
                    " ORDER BY stamp DESC"
    )
    List<CommentVo> getAllCommentVo();
}
