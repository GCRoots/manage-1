package com.manage.demo.server;

import com.manage.demo.pojo.Goods;

import java.util.List;

/**
 * @author ywx
 */
public interface CommodityServer {

    List<Goods> getAll();

    int del(String gid);

    void up(Goods goods);

    void add(Goods goods);

    List<Goods> im(Goods gid);
}
