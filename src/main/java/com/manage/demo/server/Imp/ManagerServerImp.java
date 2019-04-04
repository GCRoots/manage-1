package com.manage.demo.server.Imp;

import com.manage.demo.dao.mapper.ManagerMapper;
import com.manage.demo.pojo.Manager;
import com.manage.demo.server.ManagerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServerImp implements ManagerServer {
    @Autowired
    private ManagerMapper managerMapper;

    @Override

    public Manager findByUuid(String uuid) {
        return managerMapper.findByUuid(uuid);
    }


    public Manager login(Manager manager) {

        return managerMapper.login(manager);
    }
}
