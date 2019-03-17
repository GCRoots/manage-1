package com.manage.demo.dao;

import com.manage.demo.pojo.Manager;
import org.apache.ibatis.annotations.Param;

public interface ManagerMapper {

    Manager findByUuid(@Param("uuid") String uuid);
}
