package com.cc.service;

import com.cc.pojo.Favor;
import com.cc.pojo.vo.FavorVo;

import java.util.List;

public interface IFavorService {

    boolean save(Favor favor);

    boolean deleteById(Integer id);

    boolean deleteByUidBid(Favor favor);

    boolean verifyStar(Integer bid, Integer uid);

    List<Favor> getByUid(Integer uid);

    List<FavorVo> getAllFavorVo();
}
