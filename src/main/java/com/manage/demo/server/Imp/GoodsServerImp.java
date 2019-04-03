package com.manage.demo.server.Imp;

import com.manage.demo.dao.mapper.GoodsMapper;
import com.manage.demo.pojo.Goods;
import com.manage.demo.server.GoodsSever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsServerImp implements GoodsSever {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Goods findByGid(String gid) {
        return goodsMapper.findByGid(gid);
    }
}
