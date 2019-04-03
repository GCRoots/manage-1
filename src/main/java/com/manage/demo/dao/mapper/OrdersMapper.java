package com.manage.demo.dao.mapper;

import com.manage.demo.pojo.Orders;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersMapper {

    Orders[] findByIsdelivery(@Param("page") int page);

    Orders findByOid(@Param("oid") String oid);

    Orders[] findByIsPay(@Param("page") int page);

    Orders[] findByIsPayAndUnpay(@Param("page") int page);

}
