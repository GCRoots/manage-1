package com.manage.demo.controller;


import com.manage.demo.dao.mapper.ManagerMapper;
import com.manage.demo.pojo.Manager;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Login {

    @Autowired
    private ManagerMapper managerDao;

    @RequestMapping(value = "/home/login",method = RequestMethod.POST)

    public String login(@RequestBody JSONObject jsonObject){
        String s=jsonObject.toString();
        JSONObject json=JSONObject.fromObject(s);
        Manager user=(Manager) JSONObject.toBean(json,Manager.class);

        if (managerDao.login(user)!=null){
            return "sucess";
        }else {
            return "error";
        }


    }
}
