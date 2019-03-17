package com.manage.demo.server.Imp;

import com.manage.demo.dao.UserMapper;
import com.manage.demo.pojo.User;
import com.manage.demo.server.UserServer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServerImp implements UserServer {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUid( String uid){
        return userMapper.findByUid(uid);
    }
}
