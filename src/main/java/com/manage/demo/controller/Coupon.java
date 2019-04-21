package com.manage.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.manage.demo.dao.redis.RedisOperating;
import com.manage.demo.pojo.Coupons;
import com.manage.demo.pojo.Data;
import com.manage.demo.pojo.Message;
import com.manage.demo.server.CouponServer;
import com.manage.demo.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class Coupon {
    @Autowired
    private UserServer userServer;
    @Autowired
    private CouponServer couponServer;

    @RequestMapping(value = "/coupon/view",method = RequestMethod.POST)
    public String view(@RequestBody JSONObject json) {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uid = d.getUid();

        List<Coupons> coupons=couponServer.getAll();
        Data data = new Data();
        data.newList();
        for (int i = 0; i < coupons.size(); i++){
            Data data1 = new Data();
            Coupons coupon=coupons.get(i);
            data1.setDid(coupon.getDid());//优惠券id
            data1.setNe(coupon.getName());//??????????
            data1.setDi(coupon.getPercent());//打折
            data1.setDea(coupon.getDead_time());//dead_time
            data1.setQua(coupon.getQuanlity());//数量
            String mes = JSON.toJSONString(data1);
            data.setDa(mes);
        }

        Message m = new Message();
        m.setRs("t");
        m.setData(data);
        String rs=JSON.toJSONString(m);
        return rs;
    }

    @RequestMapping(value = "/coupon/add",method = RequestMethod.POST)
    public String add(@RequestBody JSONObject json){
        Data d= JSON.parseObject(json.toString(), Data.class);
        String uid = d.getUid();
        Map hash = new HashMap<String,Integer>();
        Map hash1 = new HashMap<String,String>();
        if (uid.equals("xxx")){
            couponServer.Add(d.getDid(),d.getNe(),d.getDi(),d.getQua(),d.getDea());
            hash.put(d.getDid(),d.getQua());
            hash1.put("nm",d.getNe());
            hash1.put("dis",d.getDi());
            hash1.put("dea",d.getDea());
            RedisOperating.hset("coupon",hash);
            RedisOperating.hset(d.getDid(),hash1);
            Message m=new Message();
            m.setRs("t");
            String rs=JSON.toJSONString(m);
            return rs;
        }
        else
            return "failed";

    }

    @RequestMapping(value = "/coupon/delete",method = RequestMethod.POST)
    public String delete(@RequestBody JSONObject json){
        Data d= JSON.parseObject(json.toString(), Data.class);
        String uid = d.getUid();
        if (uid.equals("xxx")){
            String did = d.getDi();
            Map map = RedisOperating.hgetAll("coupon");
            map.remove(d.getDid());
            RedisOperating.hset("coupon",map);
            RedisOperating.del(d.getDid());
            couponServer.deleteBydid(did);
            Message m=new Message();
            m.setRs("t");
            String rs=JSON.toJSONString(m);
            return rs;
        }
       else
           return "failed";
    }
}
