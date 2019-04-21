package com.manage.demo.server;

import com.manage.demo.pojo.Coupons;

import java.util.List;

public interface CouponServer {

    Coupons findByDid(String did);
    Coupons Add(String did, String name, String percent, String quanlity, String dead_time);
    Coupons deleteBydid(String did);
    public List<Coupons> getAll();

}