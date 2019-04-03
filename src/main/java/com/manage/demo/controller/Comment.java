package com.manage.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.manage.demo.dao.cdn.cdn;
import com.manage.demo.pojo.Data;
import com.manage.demo.pojo.Message;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Comment {
    @RequestMapping(value = "/comment/view",method = RequestMethod.POST)
    public String view(@RequestBody JSONObject json) throws IOException {
        Data d= JSON.parseObject(json.toString(), Data.class);
        String uid=d.getUid();
        if (uid.equals("xxx")){
            cdn cdn = new cdn();
            String jsonString = cdn.download("my0039/page/comm/myself/"+uid+".json","/home/zsy/桌面/my0039/page/comm/myself/"+uid+".json");
            JSONObject jb = JSON.parseObject(jsonString);
            String hd = jb.getString("hd");
            String jsonString1 = cdn.download(hd,"/home/zsy/桌面/"+hd);
            JSONObject jb1 = JSON.parseObject(jsonString1);
            String string = jb1.getString("nx");//获取当前评论的下一条的地址，用于循环遍历
            String rs = null;
            Data d2=new Data();
            Message m=new Message();
            String jsonString2 = null;
            while (string.equals("0")){
                jsonString2 = cdn.download(string,"/home/zsy/桌面/"+string);
                JSONObject jb2 = JSON.parseObject(jsonString2);
                JSONObject ctn = JSON.parseObject(jb2.getString("ctn"));//获取评论内容json，并转换成jsonobject
                d2.setUid(uid);
                d2.setCid(ctn.getString("cid"));
                d2.setNm("XX");//??????????????????????
                d2.setNam(ctn.getString("nam"));
                d2.setCom(ctn.getString("com"));
                m.setRs("t");
                m.setData(d2);
                rs+=JSON.toJSONString(m);
                string = jb2.getString("nx");
            }
            return rs;
        }
        else
            return "failed";
    }

    @RequestMapping(value = "/comment/delete",method = RequestMethod.POST)
    public String delete(@RequestBody JSONObject json) throws IOException {
        Data d= JSON.parseObject(json.toString(), Data.class);
        String uid=d.getUid();
        if (uid=="xxx"){
        String cid=d.getCid();
        cdn cdn = new cdn();
        String jsonString = cdn.download("my0039/page/comm/myself/"+uid+".json","/home/zsy/桌面/my0039/page/comm/myself/"+uid+".json");
        String jsonString1 = cdn.download("my0039/page/comm/myself/"+cid+".json","/home/zsy/桌面/my0039/page/comm/myself/"+cid+".json");
        JSONObject jb = JSON.parseObject(jsonString);
        JSONObject jb1 = JSON.parseObject(jsonString1);
        String hd = jb.getString("hd");
        String tl = jb.getString("tl");
        if (hd.equals("my0039/page/comm/myself/"+cid+".json")){
            jb.put("hd",jb1.getString("nx"));
            //如果cid为第一条评论
        }
        else if (tl.equals("my0039/page/comm/myself/"+cid+".json")){
            jb.put("tl",jb1.getString("fr"));
            //如果为最后一条评论
        }
        else{
            String fr = jb1.getString("fr");
            String nx = jb1.getString("nx");
            String fJson = cdn.download(fr,"/home/zsy/桌面/"+fr);
            String nJson = cdn.download(nx,"/home/zsy/桌面/"+nx);
            JSONObject fj = JSON.parseObject(fJson);
            JSONObject nj = JSON.parseObject(nJson);
            fj.put("nx",jb1.getString("nx"));
            nj.put("fr",jb1.getString("fr"));
            cdn.delete("my0039/page/comm/myself/"+cid+".json");
            cdn.upload(fr,"/home/zsy/桌面/"+fr);
            cdn.upload(nx,"/home/zsy/桌面/"+nx);
            //如果为中间的评论

        }

        Message m=new Message();
        m.setRs("t");
        String rs=JSON.toJSONString(m);
        return rs;}
        else {
            return "failed";
        }
    }

}
