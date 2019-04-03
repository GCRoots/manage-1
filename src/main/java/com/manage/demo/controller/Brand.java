package com.manage.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manage.demo.dao.cdn.cdn;
import com.manage.demo.pojo.Data;
import com.manage.demo.pojo.Message;
import com.manage.demo.server.BrandServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class Brand {
    @Autowired
    private BrandServer brandServer;

    @RequestMapping(value = "/brand/view",method = RequestMethod.POST)
    public String view(@RequestBody JSONObject json) throws IOException {
        Data d= JSON.parseObject(json.toString(), Data.class);
//        Brands brands = brandServer.findByname("001");
//        System.out.println(brands.getImage());
//        Brands brands1 = brandServer.findAll();
//        System.out.println(brands1);
        String uid = d.getUid();
        if (uid.equals("xxx")){
            cdn cdn = new cdn();
            String jsonString = cdn.download("my0039/page/brand.json","/home/zsy/桌面/my0039/page/brand.json");
            JSONObject jb = JSON.parseObject(jsonString);
            String lo = jb.getString("lo");

//        Data d2=new Data();//循环加入data数据
//        Data d3=new Data();
//        d2.setNe("XX");
//        d2.setIm("XXX");
//        d3.setNe("XX");
//        d3.setIm("XXX");
//        String rs1=JSON.toJSONString(d2);
//        String rs2=JSON.toJSONString(d3);
//            Data data=new Data();
//            data.newList();
//            data.setList(lo);
            Message m=new Message();
            m.setRs("t");
//            m.setData(data);
            String rs=lo;
            return rs;
        }
        else
            return "failed";

    }

    @RequestMapping(value = "/brand/add",method = RequestMethod.POST)
    public String add(@RequestBody JSONObject json) throws IOException {
        Data d= JSON.parseObject(json.toString(), Data.class);
        String uid = d.getUid();
        String ne = d.getNe();
        String im = d.getIm();
        if (uid.equals("xxx")){
            brandServer.Add(ne,im);
            cdn cdn = new cdn();
            String jsonString = cdn.download("my0039/page/brand.json","/home/zsy/桌面/my0039/page/brand.json");
            JSONObject jb = JSON.parseObject(jsonString);
            String djs = JSON.toJSONString(d);
            JSONObject jb1 = JSON.parseObject(djs);
            JSONArray ja = JSON.parseArray(jb.getString("lo"));
            ja.add(jb1);
            jb.put("lo",ja.toString());
            createJsonFile(jb.toString(),"/home/zsy/桌面/my0039/page","brand");
            cdn.upload("my0039/page/brand.json","/home/zsy/桌面/my0039/page/brand.json");
            System.out.println(djs);
            System.out.println(ja);
            System.out.println(jb);

            Message m=new Message();
            m.setRs("t");
            String rs=JSON.toJSONString(m);
            return rs;
        }
        else
            return "failed";
    }

    @RequestMapping(value = "/brand/delete",method = RequestMethod.POST)
    public String delete(@RequestBody JSONObject json) throws IOException {
        Data d= JSON.parseObject(json.toString(), Data.class);
        String uid = d.getUid();
        String ne = d.getNe();
        if (uid.equals("xxx")){
            brandServer.deleteByname(ne);
            cdn cdn = new cdn();
            String jsonString = cdn.download("my0039/page/brand.json","/home/zsy/桌面/my0039/page/brand.json");
            JSONObject jb = JSON.parseObject(jsonString);
            JSONArray ja = JSON.parseArray(jb.getString("lo"));
            ja.get(0).toString();
            System.out.println(ja.get(0).toString());
            int index = 0 ;
            for(int i=0;i<ja.size();i++){
                JSONObject jb1 = JSON.parseObject(ja.get(i).toString());
                if (jb1.getString("ne").equals(ne)){
                    index = i;
                    break;
                }
            }
            ja.remove(index);
            jb.put("lo",ja.toString());
            createJsonFile(jb.toString(),"/home/zsy/桌面/my0039/page","brand");
            cdn.upload("my0039/page/brand.json","/home/zsy/桌面/my0039/page/brand.json");
            System.out.println(ja);
            Message m=new Message();
            m.setRs("t");
            String rs=JSON.toJSONString(m);
            return rs;

        }
      else
          return "failed";
    }

    @RequestMapping(value = "/brand/viewList",method = RequestMethod.POST)
    public String  viewList(@RequestBody JSONObject json){
        Data d= JSON.parseObject(json.toString(), Data.class);
        String s1=JSON.toJSONString(d);
        Data d1=new Data();
        d1.newList();
        d1.setList(s1);
        Message m= new Message();
        m.setRs("t");
        m.setData(d1);
        String s=JSON.toJSONString(m);
        return s;
    }

    @RequestMapping(value = "/brand/viewBrand",method = RequestMethod.POST)
    public String viewBrand(@RequestBody JSONObject json){
        Data d= JSON.parseObject(json.toString(), Data.class);

        Data d1=new Data();
        d1.setNe("xxx");
        d1.setIm("xxx");

        Data d2=new Data();

        String s2=JSON.toJSONString(d1);
        d2.newList();
        d2.setList(s2);

        Message m=new Message();
        m.setData(d2);
        m.setRs("t");
        String s=JSON.toJSONString(m);
        return s;
    }

    @RequestMapping(value = "/brand/deleteBrand",method = RequestMethod.POST)
    public String deleteBrand(@RequestBody JSONObject json){
        Data d= JSON.parseObject(json.toString(), Data.class);
        Message m= new Message();
        m.setRs("t");
//        m.setData(d);
        String s=JSON.toJSONString(m);
        return s;
    }

    public static boolean createJsonFile(String jsonString, String filePath, String fileName) {
        // 标记文件生成是否成功
        boolean flag = true;

        // 拼接文件完整路径
        String fullPath = filePath + File.separator + fileName + ".json";

        // 生成json格式文件
        try {
            // 保证创建一个新文件
            File file = new File(fullPath);
            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // 如果已存在,删除旧文件
                file.delete();
            }
            file.createNewFile();

            // 将格式化后的字符串写入文件
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(jsonString);
            write.flush();
            write.close();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }

        // 返回是否成功的标记
        return flag;
    }
}
