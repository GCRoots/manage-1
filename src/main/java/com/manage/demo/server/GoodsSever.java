package com.manage.demo.server;

import com.manage.demo.pojo.Goods;

import java.util.List;

public interface GoodsSever {

    Goods findByGid(String gid);

}
