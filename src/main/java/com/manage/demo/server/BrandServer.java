package com.manage.demo.server;

import com.manage.demo.pojo.Brands;

public interface BrandServer {

    Brands findByname(String name);
    Brands findAll();
    Brands Add(String name, String image);
    Brands deleteByname(String name);

}