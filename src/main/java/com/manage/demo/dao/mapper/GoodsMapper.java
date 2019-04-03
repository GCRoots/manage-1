package com.manage.demo.dao.mapper;

import com.manage.demo.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsMapper {

    Goods findByGid(@Param("gid") String gid);


}
