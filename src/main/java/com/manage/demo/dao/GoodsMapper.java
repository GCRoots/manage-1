package com.manage.demo.dao;

import com.manage.demo.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {

    Goods findByGid(@Param("gid") String gid);


}
