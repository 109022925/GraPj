package com.cc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cc.pojo.vo.FavorVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FavorVoDao extends BaseMapper<FavorVo> {
    @Select(
            "SELECT fid, username, title \n" +
                    "FROM favor JOIN blog JOIN user\n" +
                    "WHERE favor.uid = user.uid\n" +
                    "AND favor.bid = blog.bid\n" +
                    "ORDER BY username"
    )
    List<FavorVo> getAllFavorVo();
}
