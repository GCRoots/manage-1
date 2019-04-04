package com.manage.demo.dao.mapper;

import com.manage.demo.pojo.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ywx
 */
@Repository
public interface CommodityDao {

    List<Goods> getAll();

    void up(Goods goods);

    void add(Goods goods);

    int del(String gid);

    List<Goods> im(Goods gid);

}
