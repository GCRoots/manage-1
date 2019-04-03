package com.manage.demo.dao.mapper;

import com.manage.demo.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User findByUid(@Param("uid") String uid);
}
