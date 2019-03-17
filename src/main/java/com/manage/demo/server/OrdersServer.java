package com.manage.demo.server;

import com.manage.demo.pojo.Orders;

public interface OrdersServer {

    Orders[] findByIsdelivery(String isdelivery,int page);

    Orders findByOid( String oid);

    Orders[] findByIsPay(int page);

    Orders[] findByIsPayAndUnpay(int page);

}
