package com.manage.demo.dao.mapper;

import com.manage.demo.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    public User findByUid(@Param("uid") String uid);

    public List<User> getAll();

    public User findById(String id);

    public User addUser(User user);

    public List<User> upUser();

    public void delUser(User user);
}
