package com.manage.demo.server.Imp;

import com.manage.demo.dao.mapper.UserMapper;
import com.manage.demo.pojo.User;
import com.manage.demo.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServerImp implements UserServer {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUid( String uid){
        return userMapper.findByUid(uid);
    }

    @Override
    public List<User> getAll() {

        return userMapper.getAll();
    }

    @Override
    public User findById(String id) {

        return userMapper.findById(id);
    }

    @Override
    public User addUser(User user) {

        return userMapper.addUser(user);
    }

    @Override
    public List<User> upUser() {

        return userMapper.upUser();
    }

    @Override
    public void delUser(User user) {

    }
}
