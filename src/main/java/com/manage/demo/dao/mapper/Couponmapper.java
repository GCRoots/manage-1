package com.manage.demo.dao.mapper;

import com.manage.demo.pojo.Coupons;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Couponmapper {

    Coupons findBydid(@Param("did") String did);
    Coupons Add(@Param("did") String did, @Param("name") String name, @Param("percent") String percent, @Param("quanlity") String quanlity, @Param("dead_time") String dead_time);
    Coupons deleteBydid(@Param("did") String did);
    public List<Coupons> getAll();


}
