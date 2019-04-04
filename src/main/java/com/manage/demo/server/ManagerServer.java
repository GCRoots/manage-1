package com.manage.demo.server;

import com.manage.demo.pojo.Manager;

public interface ManagerServer {
    Manager findByUuid(String uuid);


    public Manager login(Manager manager);

}
