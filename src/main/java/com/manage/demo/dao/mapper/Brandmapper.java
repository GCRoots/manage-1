package com.manage.demo.dao.mapper;
import com.manage.demo.pojo.Brands;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Brandmapper {

    Brands findByname(@Param("name") String name);
    Brands findAll();
    Brands Add(@Param("name") String name, @Param("image") String image);
    Brands deleteByname(@Param("name") String name);




}
