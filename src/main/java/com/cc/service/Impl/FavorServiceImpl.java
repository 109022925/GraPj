package com.cc.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cc.dao.FavorDao;
import com.cc.dao.FavorVoDao;
import com.cc.pojo.Favor;
import com.cc.pojo.vo.FavorVo;
import com.cc.service.IFavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FavorServiceImpl extends ServiceImpl<FavorDao, Favor> implements IFavorService {

    @Autowired
    private FavorDao favorDao;

    @Autowired
    private FavorVoDao favorVoDao;

    @Override
    public boolean save(Favor favor) {
        return favorDao.insert(favor) > 0;
    }

    @Override
    public boolean deleteById(Integer id) {
        return favorDao.deleteById(id) > 0;
    }

    @Override
    public boolean deleteByUidBid(Favor favor) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", favor.getUid());
        map.put("bid", favor.getBid());
        return favorDao.deleteByMap(map) > 0;
    }

    @Override
    public boolean verifyStar(Integer bid, Integer uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("bid", bid);
        map.put("uid", uid);
        if (favorDao.selectByMap(map).isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public List<Favor> getByUid(Integer uid) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        return favorDao.selectByMap(map);
    }

    @Override
    public List<FavorVo> getAllFavorVo() {
        return favorVoDao.getAllFavorVo();
    }
}
