package com.manage.demo.dao.mapper;

import com.manage.demo.pojo.Manager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerMapper {

    Manager findByUuid(@Param("uuid") String uuid);

    public Manager login(Manager manager);
}
