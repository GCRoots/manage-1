package com.manage.demo.server.Imp;

import com.manage.demo.dao.mapper.Couponmapper;
import com.manage.demo.pojo.Coupons;
import com.manage.demo.server.CouponServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponServerImp implements CouponServer {

    @Autowired
    private Couponmapper couponmapper;

    @Override
    public Coupons findByDid(String did) {
        return couponmapper.findBydid(did);
    }

    @Override
    public Coupons Add(String did, String name, String percent, String quanlity, String dead_time) {
        return couponmapper.Add(did, name, percent, quanlity, dead_time);
    }

    @Override
    public Coupons deleteBydid(String did) {
        return null;
    }
}
