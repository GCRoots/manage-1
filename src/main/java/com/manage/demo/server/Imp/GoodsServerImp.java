package com.manage.demo.server.Imp;

import com.manage.demo.dao.GoodsMapper;
import com.manage.demo.pojo.Goods;
import com.manage.demo.server.GoodsSever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServerImp implements GoodsSever {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Goods findByGid(String gid) {
        return goodsMapper.findByGid(gid);
    }
}
