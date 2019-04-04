package com.manage.demo.server.Imp;

import com.manage.demo.dao.mapper.CommodityDao;
import com.manage.demo.pojo.Goods;
import com.manage.demo.server.CommodityServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "CommodityServer")
public class CommodityImp implements CommodityServer {

    @Autowired
    CommodityDao commodityDao;

    @Override
    public List<Goods> getAll() {
        return commodityDao.getAll();
    }

    @Override
    public int del(String gid) {
        return commodityDao.del(gid);
    }

    @Override
    public void up(Goods goods) {

    }

    @Override
    public void add(Goods goods) {

    }
    @Override
    public List<Goods> im(Goods gid){
        return commodityDao.im(gid);
    }

}
