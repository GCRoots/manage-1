package com.manage.demo.server;

import com.manage.demo.pojo.User;

import java.util.List;

public interface UserServer {

    public User findByUid(String uid);

    public List<User> getAll();

    public User findById(String id);

    public User addUser(User user);

    public List<User> upUser();

    public void delUser(User user);
}
