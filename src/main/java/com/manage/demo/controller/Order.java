package com.manage.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manage.demo.pojo.*;
import com.manage.demo.server.GoodsSever;
import com.manage.demo.server.ManagerServer;
import com.manage.demo.server.OrdersServer;
import com.manage.demo.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Order {


    @Autowired
    private ManagerServer managerServer;

    @Autowired
    private OrdersServer ordersServer;

    @Autowired
    private GoodsSever goodsSever;

    @Autowired
    private UserServer userServer;

    @RequestMapping(value = "/order/delivery", method = RequestMethod.POST)
    public String delivery(@RequestBody JSONObject json) {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {

            String isDelivery="已发货";
            int page=(Integer.parseInt(d.getPag())-1)*50;
            Orders[] orders=ordersServer.findByIsdelivery(isDelivery,page);
            Data data = new Data();
            data.newList();
            for (Orders order:orders) {

                String oid=order.getOid();
                String uid=order.getUid();
                String gidSizeQuas=order.getGid_size_qua();
                String pri=order.getPrice();
                String dis=order.getDiscount();
                String act=order.getActual();
                String tim=order.getOrder_time();

                User user=userServer.findByUid(uid);
                String nm=user.getName();
                String[] gidSizeQua=gidSizeQuas.split(",");
                Data dataGds = new Data();
                dataGds.newList();
                String gds=new String();
                for (String gsqs:gidSizeQua){

                    String[] gsq=gsqs.split("_");
                    String gid=gsq[0];
                    String siz=gsq[1];
                    String qua=gsq[2];

                    Goods good=goodsSever.findByGid(gid);
                    String nam=good.getTitle();
                    String col=good.getColor();
                    String pro=good.getPro_code();

                    Data dataGds1=new Data();
                    dataGds1.setSiz(siz);
                    dataGds1.setQua(qua);
                    dataGds1.setNam(nam);
                    dataGds1.setCol(col);
                    dataGds1.setPro(pro);

                    gds=gds+JSON.toJSONString(dataGds1)+",";
                    dataGds.setDa(JSON.toJSONString(dataGds1));
                }

                gds=gds.substring(0,gds.length()-1);

                Data data1=new Data();
                data1.setNm(nm);
                data1.setPri(pri);
                data1.setDis(dis);
                data1.setAct(act);
                data1.setTim(tim);
                data1.newList();
                data1.setDa(gds);
                data.setDa(JSON.toJSONString(data1));
            }

            Message m = new Message();
            m.setRs("t");
            m.setData(data);

            String s = JSON.toJSONString(m);
            return s;


        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/order/detail", method = RequestMethod.POST)
    public String detail(@RequestBody JSONObject json) {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String oid=d.getOid();

            Orders order=ordersServer.findByOid(oid);
            String uid=order.getUid();
            String gidSizeQuas=order.getGid_size_qua();
            String pri=order.getPrice();
            String dis=order.getDiscount();
            String act=order.getActual();
            String tim=order.getOrder_time();

            User user=userServer.findByUid(uid);
            String nm=user.getName();

            String[] gidSizeQua=gidSizeQuas.split(",");
            Data data = new Data();
            data.newList();

            for (String gsqs:gidSizeQua){

                String[] gsq=gsqs.split("_");
                String gid=gsq[0];
                String siz=gsq[1];
                String qua=gsq[2];

                Goods good=goodsSever.findByGid(gid);
                String nam=good.getTitle();
                String col=good.getColor();
                String pro=good.getPro_code();

                Data dataGds=new Data();
                dataGds.setSiz(siz);
                dataGds.setQua(qua);
                dataGds.setNam(nam);
                dataGds.setCol(col);
                dataGds.setPro(pro);
                data.setDa(JSON.toJSONString(dataGds));
            }

            data.setNm(nm);
            data.setPri(pri);
            data.setDis(dis);
            data.setAct(act);
            data.setTim(tim);

            Message m = new Message();
            m.setData(data);
            m.setRs("t");

            String s = JSON.toJSONString(m);
            return s;


        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/order/undelivery", method = RequestMethod.POST)
    public String undelivery(@RequestBody JSONObject json) {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {

            String isDelivery="未发货";
            int page=(Integer.parseInt(d.getPag())-1)*50;
            Orders[] orders=ordersServer.findByIsdelivery(isDelivery,page);
            Data data = new Data();
            data.newList();
            for (Orders order:orders) {

                String oid=order.getOid();
                String uid=order.getUid();
                String gidSizeQuas=order.getGid_size_qua();
                String pri=order.getPrice();
                String dis=order.getDiscount();
                String act=order.getActual();
                String tim=order.getOrder_time();

                User user=userServer.findByUid(uid);
                String nm=user.getName();

                String[] gidSizeQua=gidSizeQuas.split(",");
                Data dataGds = new Data();
                dataGds.newList();
                String gds=new String();
                for (String gsqs:gidSizeQua){

                    String[] gsq=gsqs.split("_");
                    String gid=gsq[0];
                    String siz=gsq[1];
                    String qua=gsq[2];

                    Goods good=goodsSever.findByGid(gid);
                    String nam=good.getTitle();
                    String col=good.getColor();
                    String pro=good.getPro_code();

                    Data dataGds1=new Data();
                    dataGds1.setSiz(siz);
                    dataGds1.setQua(qua);
                    dataGds1.setNam(nam);
                    dataGds1.setCol(col);
                    dataGds1.setPro(pro);

                    gds=gds+JSON.toJSONString(dataGds1)+",";
                    dataGds.setDa(JSON.toJSONString(dataGds1));
                }


                gds=gds.substring(0,gds.length()-1);

                Data data1=new Data();
                data1.setNm(nm);
                data1.setPri(pri);
                data1.setDis(dis);
                data1.setAct(act);
                data1.setTim(tim);
                data1.newList();
                data1.setDa(gds);
                data.setDa(JSON.toJSONString(data1));
            }

            Message m = new Message();
            m.setRs("t");
            m.setData(data);

            String s = JSON.toJSONString(m);
            return s;


        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/order/unpay", method = RequestMethod.POST)
    public String unpay(@RequestBody JSONObject json) {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {

            int page=(Integer.parseInt(d.getPag())-1)*50;
            Orders[] orders=ordersServer.findByIsPay(page);
            Data data = new Data();
            data.newList();
            for (Orders order:orders) {

                String oid=order.getOid();
                String uid=order.getUid();
                String gidSizeQuas=order.getGid_size_qua();
                String pri=order.getPrice();
                String dis=order.getDiscount();
                String act=order.getActual();
                String tim=order.getOrder_time();

                User user=userServer.findByUid(uid);
                String nm=user.getName();
                String[] gidSizeQua=gidSizeQuas.split(",");
                Data dataGds = new Data();
                dataGds.newList();
                String gds=new String();
                for (String gsqs:gidSizeQua){

                    String[] gsq=gsqs.split("_");
                    String gid=gsq[0];
                    String siz=gsq[1];
                    String qua=gsq[2];

                    Goods good=goodsSever.findByGid(gid);
                    String nam=good.getTitle();
                    String col=good.getColor();
                    String pro=good.getPro_code();

                    Data dataGds1=new Data();
                    dataGds1.setSiz(siz);
                    dataGds1.setQua(qua);
                    dataGds1.setNam(nam);
                    dataGds1.setCol(col);
                    dataGds1.setPro(pro);

                    gds=gds+JSON.toJSONString(dataGds1)+",";
                    dataGds.setDa(JSON.toJSONString(dataGds1));
                }

                gds=gds.substring(0,gds.length()-1);

                Data data1=new Data();
                data1.setNm(nm);
                data1.setPri(pri);
                data1.setDis(dis);
                data1.setAct(act);
                data1.setTim(tim);
                data1.newList();
                data1.setDa(gds);
                data.setDa(JSON.toJSONString(data1));
            }

            Message m = new Message();
            m.setRs("t");
            m.setData(data);

            String s = JSON.toJSONString(m);
            return s;


        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/order/other", method = RequestMethod.POST)
    public String other(@RequestBody JSONObject json) {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager = managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {

            int page = (Integer.parseInt(d.getPag()) - 1) * 50;
            Orders[] orders = ordersServer.findByIsPay(page);
            Data data = new Data();
            data.newList();
            for (Orders order : orders) {

                String oid = order.getOid();
                String uid = order.getUid();
                String gidSizeQuas = order.getGid_size_qua();
                String pri = order.getPrice();
                String dis = order.getDiscount();
                String act = order.getActual();
                String tim = order.getOrder_time();

                User user = userServer.findByUid(uid);
                String nm = user.getName();
                String[] gidSizeQua = gidSizeQuas.split(",");
                Data dataGds = new Data();
                dataGds.newList();
                String gds = new String();
                for (String gsqs : gidSizeQua) {

                    String[] gsq = gsqs.split("_");
                    String gid = gsq[0];
                    String siz = gsq[1];
                    String qua = gsq[2];

                    Goods good = goodsSever.findByGid(gid);
                    String nam = good.getTitle();
                    String col = good.getColor();
                    String pro = good.getPro_code();

                    Data dataGds1 = new Data();
                    dataGds1.setSiz(siz);
                    dataGds1.setQua(qua);
                    dataGds1.setNam(nam);
                    dataGds1.setCol(col);
                    dataGds1.setPro(pro);

                    gds = gds + JSON.toJSONString(dataGds1) + ",";
                    dataGds.setDa(JSON.toJSONString(dataGds1));
                }

                gds = gds.substring(0, gds.length() - 1);

                Data data1 = new Data();
                data1.setNm(nm);
                data1.setPri(pri);
                data1.setDis(dis);
                data1.setAct(act);
                data1.setTim(tim);
                data1.newList();
                data1.setDa(gds);
                data.setDa(JSON.toJSONString(data1));
            }

            Message m = new Message();
            m.setRs("t");
            m.setData(data);

            String s = JSON.toJSONString(m);
            return s;


        } else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

}
