package com.manage.demo.server;

import com.manage.demo.pojo.User;

public interface UserServer {

    User findByUid(String uid);
}
