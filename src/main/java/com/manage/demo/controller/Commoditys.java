package com.manage.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manage.demo.dao.mapper.CommodityDao;
import com.manage.demo.pojo.Goods;
import com.manage.demo.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author ywx
 */
@RestController
public class Commoditys {

    @Autowired
    CommodityDao commodityDao;

    @RequestMapping(value = "/goods/views" , method = RequestMethod.POST)
    public JSONArray all(){

        List all = commodityDao.getAll();

        JSONArray resJson=JSONArray.parseArray(String.valueOf(all));
        System.out.println(resJson.toString());

        return resJson;
    }

    @RequestMapping(value = "/goods/uploadSingle" , method = RequestMethod.POST)
    public String adds(@RequestBody JSONObject json){

        String s = json.toString();
        Goods goods = (Goods) JSONObject.parseObject(s,Goods.class);
        commodityDao.add(goods);

        return "t";
    }

    @RequestMapping(value = "/goods/delete" , method = RequestMethod.POST)
    public String Goodsdel(@RequestBody JSONObject json){
        String s = json.toString();

        commodityDao.del(s);
        return "t";
    }

    @RequestMapping(value = "/goods/image",method = RequestMethod.POST)
    public JSONArray image(@RequestBody JSONObject json){

        String s = json.toString();
        Goods goods = (Goods) JSONObject.parseObject(s,Goods.class);

        List image = commodityDao.im(goods);

        JSONArray im = JSONArray.parseArray(String.valueOf(image));
        return im;
    }
//    @RequestMapping(value = "goods/es" , method = RequestMethod.POST)
//    public String Goods(String path) throws Exception{
//
//        EsContronl();
//        addInformation("Goods","goods", "1",readCSVFile(path));
//
//        return "t";
//    }

    @RequestMapping(value = "/goods/uploadCsv",method = RequestMethod.POST)
    public String uploadCsv(MultipartFile file) throws IOException {
        Message m=new Message();
        String filename=file.getOriginalFilename();
        String postfix = filename.substring(filename.lastIndexOf(".")+1);
        System.out.println(postfix);
        if(postfix=="csv"){
            m.setRs("t");
        }else{
            m.setRs("f");
        }
        String rs=JSON.toJSONString(m);

        return rs;
    }
}