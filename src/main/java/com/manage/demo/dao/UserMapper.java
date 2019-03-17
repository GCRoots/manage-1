package com.manage.demo.dao;

import com.manage.demo.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User findByUid(@Param("uid") String uid);
}
