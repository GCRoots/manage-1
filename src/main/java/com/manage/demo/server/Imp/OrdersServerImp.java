package com.manage.demo.server.Imp;

import com.manage.demo.dao.OrdersMapper;
import com.manage.demo.pojo.Orders;
import com.manage.demo.server.OrdersServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServerImp implements OrdersServer {
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public Orders[] findByIsdelivery(String isdelivery,int page){
        Orders[] orders=ordersMapper.findByIsdelivery(page);
        List<Orders> list=new ArrayList<>();
        for (Orders order:orders){
            if (order.getIsdelivery().equals(isdelivery)){
                list.add(order);
            }
        }
        Orders[] result= list.toArray(new Orders[0]);

        return result;
    }

    @Override
    public Orders findByOid(String oid){
        return ordersMapper.findByOid(oid);
    }

    @Override
    public Orders[] findByIsPay(int page){
        Orders[] orders=ordersMapper.findByIsPay(page);
        List<Orders> list=new ArrayList<>();
        for (Orders order:orders){
            if (order.getIsPay().equals("未支付")){
                list.add(order);
            }
        }
        Orders[] result= list.toArray(new Orders[0]);
        return result;
    }

    @Override
    public Orders[] findByIsPayAndUnpay(int page){
        Orders[] orders=ordersMapper.findByIsPayAndUnpay(page);
        List<Orders> list=new ArrayList<>();
        for (Orders order:orders){
            if (!order.getIsPay().equals("未支付")){
                list.add(order);
            }
        }
        Orders[] result= list.toArray(new Orders[0]);
        return result;
    }

}
