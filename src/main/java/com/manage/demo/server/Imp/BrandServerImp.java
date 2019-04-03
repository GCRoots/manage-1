package com.manage.demo.server.Imp;

import com.manage.demo.dao.mapper.Brandmapper;
import com.manage.demo.pojo.Brands;
import com.manage.demo.server.BrandServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServerImp implements BrandServer {

    @Autowired
    private Brandmapper brandmapper;

    @Override
    public Brands findByname(String name) {
        return brandmapper.findByname(name);
    }

    @Override
    public Brands findAll() {
        return brandmapper.findAll();
    }

    @Override
    public Brands Add(String name,String image) {
        return brandmapper.Add(name,image);
    }

    @Override
    public Brands deleteByname(String name) {
        return brandmapper.deleteByname(name);
    }


}
