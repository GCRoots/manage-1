package com.manage.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manage.demo.server.GoodsSever;
import com.manage.demo.pojo.*;
import com.manage.demo.server.GoodsSever;
import com.manage.demo.server.Imp.GoodsServerImp;
import com.manage.demo.server.ManagerServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RestController
public class module {

    @Autowired
    private GoodsSever goodsSever;

    @Autowired
    private ManagerServer managerServer;

    @RequestMapping(value = "/module/banner", method = RequestMethod.POST)
    public String banner(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String string = CDN.download("my0039/page/banner.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String banner = jsonObject.getString("ban");
            String pic = null;
            JSONArray jsonArray = JSON.parseArray(banner);
            Data data = new Data();
            data.newList();
            for (int i = 0; i < jsonArray.size(); i++) {
                Data data1 = new Data();
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                data1.setNe(jsonObject1.getString("ne"));
                data1.setIm(jsonObject1.getString("im"));
                data1.setLik(jsonObject1.getString("lik"));
                String mes = JSON.toJSONString(data1);
                data.setDa(mes);
            }
            Message m = new Message();
            m.setData(data);
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/DeleteBanner", method = RequestMethod.POST)
    public String DeleteBanner(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String in = d.getIn();
            String string = CDN.download("my0039/page/banner.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String banner = jsonObject.getString("ban");
            JSONArray jsonArray = JSON.parseArray(banner);
            for (int i = 0; i < jsonArray.size(); i++) {
                if (jsonArray.getJSONObject(i).getString("in").equals(in)) {
                    jsonArray.remove(i);
                }
            }
            banner = jsonArray.toJSONString();
            jsonObject.put("ban", banner);
            string = jsonObject.toJSONString();

            String currentUser = System.getProperty("user.name");
            File file = new File("/home/" + currentUser + "/Desktop/Json.json");
            file.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(string);
            writer.flush();
            writer.close();
            CDN.upload(file, "my0039/page/banner.json");
            Message m = new Message();
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/AlterBanner", method = RequestMethod.POST)
    public String AlterBanner(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String in = d.getIn();
            String ne = d.getNe();
            String im = d.getIm();
            String lik = d.getLik();
            im = CDN.picture(im, ne, "banner");
            String string = CDN.download("my0039/page/banner.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String banner = jsonObject.getString("ban");
            JSONArray jsonArray = JSON.parseArray(banner);
            for (int i = 0; i < jsonArray.size(); i++) {
                if (jsonArray.getJSONObject(i).getString("in").equals(in)) {
                    jsonArray.getJSONObject(i).put("ne", ne);
                    jsonArray.getJSONObject(i).put("im", im);
                    jsonArray.getJSONObject(i).put("lik", lik);
                }
            }
            banner = jsonArray.toJSONString();
            jsonObject.put("ban", banner);
            string = jsonObject.toJSONString();
            String currentUser = System.getProperty("user.name");
            File file = new File("/home/" + currentUser + "/Desktop/Json.json");
            file.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(string);
            writer.flush();
            writer.close();
            CDN.upload(file, "my0039/page/banner.json");
            Message m = new Message();
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/AddBanner", method = RequestMethod.POST)
    public String AddBanner(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String ne = d.getNe();
            String im = d.getIm();
            String lik = d.getLik();
            im = CDN.picture(im, ne, "banner");
            String string = CDN.download("my0039/page/banner.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String banner = jsonObject.getString("ban");
            JSONArray jsonArray = JSON.parseArray(banner);
            String in = String.valueOf(jsonArray.size() + 1);
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("in", in);
            jsonObject1.put("ne", ne);
            jsonObject1.put("im", im);
            jsonObject1.put("lik", lik);
            jsonArray.add(jsonObject1);
            banner = jsonArray.toJSONString();
            jsonObject.put("ban", banner);
            string = jsonObject.toJSONString();
            String currentUser = System.getProperty("user.name");
            File file = new File("/home/" + currentUser + "/Desktop/Json.json");
            file.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(string);
            writer.flush();
            writer.close();
            CDN.upload(file, "my0039/page/banner.json");
            Message m = new Message();
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/home", method = RequestMethod.POST)
    public String home(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();
        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String string = CDN.download("my0039/page/home.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String home = jsonObject.getString("hom");
            String pic = null;
            JSONArray jsonArray = JSON.parseArray(home);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                if (jsonObject1.containsKey("pic")) {
                    pic = jsonObject1.getString("pic");
                }
            }
            JSONArray jsonArray1 = JSON.parseArray(pic);
            Data data = new Data();
            data.newList();
            for (int i = 0; i < jsonArray1.size(); i++) {
                Data data1 = new Data();
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                data1.setNe(jsonObject1.getString("ne"));
                data1.setIm(jsonObject1.getString("im"));
                data1.setLik(jsonObject1.getString("lik"));
                String mes = JSON.toJSONString(data1);
                data.setDa(mes);
            }
            Message m = new Message();
            m.setData(data);
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else{
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/AlterHome", method = RequestMethod.POST)
    public String AlterHome(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String in = d.getIn();
            String ne = d.getNe();
            String im = d.getIm();
            String lik = d.getLik();
            im = CDN.picture(im, ne, "home");
            String string = CDN.download("my0039/page/home.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String home = jsonObject.getString("hom");
            JSONArray jsonArray = JSON.parseArray(home);
            for (int i = 0; i < jsonArray.size(); i++) {
                if (jsonArray.getJSONObject(i).containsKey("pic")) {
                    String pic = jsonArray.getJSONObject(i).getString("pic");
                    JSONArray jsonArray1 = JSON.parseArray(pic);

                    for (int j = 0; j < jsonArray1.size(); j++) {
                        if (jsonArray1.getJSONObject(j).getString("in").equals(in)) {
                            jsonArray1.getJSONObject(j).put("ne", ne);
                            jsonArray1.getJSONObject(j).put("im", im);
                            jsonArray1.getJSONObject(j).put("lik", lik);
                        }
                    }
                    pic = jsonArray1.toJSONString();
                    jsonArray.getJSONObject(i).put("pic", pic);
                }
            }
            home = jsonArray.toJSONString();
            jsonObject.put("hom", home);

            string = jsonObject.toJSONString();
            String currentUser = System.getProperty("user.name");
            File file = new File("/home/" + currentUser + "/Desktop/Json.json");
            file.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(string);
            writer.flush();
            writer.close();
            CDN.upload(file, "my0039/page/home.json");
            Message m = new Message();
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/goods", method = RequestMethod.POST)
    public String goods(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String string = CDN.download("my0039/page/home.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String home = jsonObject.getString("hom");
            String gds = null;
            JSONArray jsonArray = JSON.parseArray(home);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                if (jsonObject1.containsKey("gds")) {
                    gds = jsonObject1.getString("gds");
                }
            }
            JSONArray jsonArray1 = JSON.parseArray(gds);
            Data data = new Data();
            data.newList();
            for (int i = 0; i < jsonArray1.size(); i++) {
                Data data1 = new Data();
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                data1.setGid(jsonObject1.getString("gid"));
                data1.setNam(jsonObject1.getString("nam"));
                data1.setPri(jsonObject1.getString("pri"));
                data1.setDis(jsonObject1.getString("dis"));
                data1.setIm1(jsonObject1.getString("im1"));
                data1.setIm2(jsonObject1.getString("im2"));
                String mes = JSON.toJSONString(data1);
                data.setDa(mes);
            }
            Message m = new Message();
            m.setData(data);
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else{
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/title", method = RequestMethod.POST)
    public String title(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String tit = d.getTit();
            String string = CDN.download("my0039/page/home.json");
            JSONObject jsonObject = JSON.parseObject(string);
            jsonObject.put("tit", tit);
            string = jsonObject.toString();
            String currentUser = System.getProperty("user.name");
            File file = new File("/home/" + currentUser + "/Desktop/Json.json");
            file.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(string);
            writer.flush();
            writer.close();
            CDN.upload(file, "my0039/page/home.json");
            Message m = new Message();
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/addGoods", method = RequestMethod.POST)
    public String addGoods(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String gid = d.getGid();
            Goods goods = goodsSever.findByGid(gid);
//        String gid=goods.getGid();
            String nam = goods.getTitle();
            String pri = goods.getPrice();
            String dis = goods.getDiscount();
            String im = goods.getIm();

            String im1 = null;
            String im2 = null;
            String[] ims = im.split("https:");
            if (ims.length > 3) {
                im1 = "https:" + ims[1];
                im2 = "https:" + ims[2];
                im1 = CDN.picture(im1, gid + "-home01", "goods");
                im2 = CDN.picture(im2, gid + "-home02", "goods");

            } else {
                im1 = "https:" + ims[1];
                im1 = CDN.picture(im1, gid + "-home01", "goods");
            }
            String string = CDN.download("my0039/page/home.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String home = jsonObject.getString("hom");
            JSONArray jsonArray = JSON.parseArray(home);
            JSONArray jsonArrayGds = new JSONArray();
            String gds = null;
            for (int i = 0; i < jsonArray.size(); i++) {
                String str = jsonArray.getString(i);
                JSONObject jsonObjectI = JSON.parseObject(str);
                if (jsonObjectI.containsKey("gds")) {
                    gds = jsonObjectI.getString("gds");
                }
            }
            jsonArrayGds = JSON.parseArray(gds);
            JSONObject jsonObjectGds = new JSONObject();
            jsonObjectGds.put("gid", gid);
            jsonObjectGds.put("nam", nam);
            jsonObjectGds.put("pri", pri);
            jsonObjectGds.put("dis", dis);
            jsonObjectGds.put("im1", im1);
            jsonObjectGds.put("im2", im2);
            jsonArrayGds.add(jsonObjectGds);
            gds = jsonArrayGds.toJSONString();
            for (int i = 0; i < jsonArray.size(); i++) {
                String str = jsonArray.getString(i);
                JSONObject jsonObjectI = JSON.parseObject(str);
                if (jsonObjectI.containsKey("gds")) {
                    jsonArray.getJSONObject(i).put("gds", gds);
                }
            }
            home = jsonArray.toJSONString();
            jsonObject.put("hom", home);

            string = jsonObject.toJSONString();
            String currentUser = System.getProperty("user.name");
            File file = new File("/home/" + currentUser + "/Desktop/Json.json");
            file.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(string);
            writer.flush();
            writer.close();

            CDN.upload(file, "my0039/page/home.json");
            Message m = new Message();
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/man", method = RequestMethod.POST)
    public String man(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String string = CDN.download("my0039/page/man.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String man = jsonObject.getString("man");
            String pic = null;
            JSONArray jsonArray = JSON.parseArray(man);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                if (jsonObject1.containsKey("pic")) {
                    pic = jsonObject1.getString("pic");
                }
            }
            JSONArray jsonArray1 = JSON.parseArray(pic);
            Data data = new Data();
            data.newList();
            for (int i = 0; i < jsonArray1.size(); i++) {
                Data data1 = new Data();
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                data1.setNe(jsonObject1.getString("ne"));
                data1.setIm(jsonObject1.getString("im"));
                data1.setLik(jsonObject1.getString("lik"));
                String mes = JSON.toJSONString(data1);
                data.setDa(mes);
            }
            Message m = new Message();
            m.setData(data);
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/alterMan", method = RequestMethod.POST)
    public String alterMan(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String in = d.getIn();
            String ne = d.getNe();
            String im = d.getIm();
            String lik = d.getLik();
            im = CDN.picture(im, ne, "man");
            String string = CDN.download("my0039/page/man.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String man = jsonObject.getString("man");
            JSONArray jsonArray = JSON.parseArray(man);
            for (int i = 0; i < jsonArray.size(); i++) {
                if (jsonArray.getJSONObject(i).containsKey("pic")) {
                    String pic = jsonArray.getJSONObject(i).getString("pic");
                    JSONArray jsonArray1 = JSON.parseArray(pic);

                    for (int j = 0; j < jsonArray1.size(); j++) {
                        System.out.println(jsonArray1.getJSONObject(j).getString("in"));
                        if (jsonArray1.getJSONObject(j).getString("in").equals(in)) {
                            jsonArray1.getJSONObject(j).put("ne", ne);
                            jsonArray1.getJSONObject(j).put("im", im);
                            jsonArray1.getJSONObject(j).put("lik", lik);
                        }
                    }
                    pic = jsonArray1.toJSONString();
                    jsonArray.getJSONObject(i).put("pic", pic);
                }
            }
            man = jsonArray.toJSONString();
            jsonObject.put("man", man);

            string = jsonObject.toJSONString();
            String currentUser = System.getProperty("user.name");
            File file = new File("/home/" + currentUser + "/Desktop/Json.json");
            file.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(string);
            writer.flush();
            writer.close();
            CDN.upload(file, "my0039/page/man.json");

            Message m = new Message();
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/mans", method = RequestMethod.POST)
    public String mans(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String string = CDN.download("my0039/page/man.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String chd = jsonObject.getString("man");
            String gds = null;
            JSONArray jsonArray = JSON.parseArray(chd);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                if (jsonObject1.containsKey("gds")) {
                    gds = jsonObject1.getString("gds");
                }
            }
            JSONArray jsonArray1 = JSON.parseArray(gds);
            Data data = new Data();
            data.newList();
            for (int i = 0; i < jsonArray1.size(); i++) {
                Data data1 = new Data();
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                data1.setGid(jsonObject1.getString("gid"));
                data1.setNam(jsonObject1.getString("nam"));
                data1.setPri(jsonObject1.getString("pri"));
                data1.setDis(jsonObject1.getString("dis"));
                data1.setIm1(jsonObject1.getString("im1"));
                data1.setIm2(jsonObject1.getString("im2"));
                String mes = JSON.toJSONString(data1);
                data.setDa(mes);
            }
            Message m = new Message();
            m.setData(data);
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/addMans", method = RequestMethod.POST)
    public String addMans(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String gid = d.getGid();
            Goods goods = goodsSever.findByGid(gid);
//        String gid=goods.getGid();
            String nam = goods.getTitle();
            String pri = goods.getPrice();
            String dis = goods.getDiscount();
            String im = goods.getIm();

            String im1 = null;
            String im2 = null;
            String[] ims = im.split("https:");
            if (ims.length > 3) {
                im1 = "https:" + ims[1];
                im2 = "https:" + ims[2];
                im1 = CDN.picture(im1, gid + "-man01", "goods");
                im2 = CDN.picture(im2, gid + "-man02", "goods");

            } else {
                im1 = "https:" + ims[1];
                im1 = CDN.picture(im1, gid + "-man01", "goods");
            }
            String string = CDN.download("my0039/page/man.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String man = jsonObject.getString("man");
            JSONArray jsonArray = JSON.parseArray(man);
            JSONArray jsonArrayGds = new JSONArray();
            String gds = null;
            for (int i = 0; i < jsonArray.size(); i++) {
                String str = jsonArray.getString(i);
                JSONObject jsonObjectI = JSON.parseObject(str);
                if (jsonObjectI.containsKey("gds")) {
                    gds = jsonObjectI.getString("gds");
                }
            }
            jsonArrayGds = JSON.parseArray(gds);
            JSONObject jsonObjectGds = new JSONObject();
            jsonObjectGds.put("gid", gid);
            jsonObjectGds.put("nam", nam);
            jsonObjectGds.put("pri", pri);
            jsonObjectGds.put("dis", dis);
            jsonObjectGds.put("im1", im1);
            jsonObjectGds.put("im2", im2);
            jsonArrayGds.add(jsonObjectGds);
            gds = jsonArrayGds.toJSONString();
            for (int i = 0; i < jsonArray.size(); i++) {
                String str = jsonArray.getString(i);
                JSONObject jsonObjectI = JSON.parseObject(str);
                if (jsonObjectI.containsKey("gds")) {
                    jsonArray.getJSONObject(i).put("gds", gds);
                }
            }
            man = jsonArray.toJSONString();
            jsonObject.put("man", man);
            string = jsonObject.toJSONString();
            String currentUser = System.getProperty("user.name");
            File file = new File("/home/" + currentUser + "/Desktop/Json.json");
            file.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(string);
            writer.flush();
            writer.close();
            CDN.upload(file, "my0039/page/man.json");
            Message m = new Message();
            m.setRs("t");
            String s = JSON.toJSONString(m);

            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/women", method = RequestMethod.POST)
    public String women(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String string = CDN.download("my0039/page/women.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String women = jsonObject.getString("women");
            String pic = null;
            JSONArray jsonArray = JSON.parseArray(women);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                if (jsonObject1.containsKey("pic")) {
                    pic = jsonObject1.getString("pic");
                }
            }
            JSONArray jsonArray1 = JSON.parseArray(pic);
            Data data = new Data();
            data.newList();
            for (int i = 0; i < jsonArray1.size(); i++) {
                Data data1 = new Data();
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                data1.setNe(jsonObject1.getString("ne"));
                data1.setIm(jsonObject1.getString("im"));
                data1.setLik(jsonObject1.getString("lik"));
                String mes = JSON.toJSONString(data1);
                data.setDa(mes);
            }
            Message m = new Message();
            m.setData(data);
            m.setRs("t");
            String s = JSON.toJSONString(m);
            System.out.println(s);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/alterWomen", method = RequestMethod.POST)
    public String alterWomen(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String in = d.getIn();
            String ne = d.getNe();
            String im = d.getIm();
            String lik = d.getLik();
            im = CDN.picture(im, ne, "women");
            String string = CDN.download("my0039/page/women.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String women = jsonObject.getString("women");
            JSONArray jsonArray = JSON.parseArray(women);
            for (int i = 0; i < jsonArray.size(); i++) {
                if (jsonArray.getJSONObject(i).containsKey("pic")) {
                    String pic = jsonArray.getJSONObject(i).getString("pic");
                    JSONArray jsonArray1 = JSON.parseArray(pic);

                    for (int j = 0; j < jsonArray1.size(); j++) {
                        System.out.println(jsonArray1.getJSONObject(j).getString("in"));
                        if (jsonArray1.getJSONObject(j).getString("in").equals(in)) {
                            jsonArray1.getJSONObject(j).put("ne", ne);
                            jsonArray1.getJSONObject(j).put("im", im);
                            jsonArray1.getJSONObject(j).put("lik", lik);
                        }
                    }
                    pic = jsonArray1.toJSONString();
                    jsonArray.getJSONObject(i).put("pic", pic);
                }
            }
            women = jsonArray.toJSONString();
            jsonObject.put("women", women);

            string = jsonObject.toJSONString();
            String currentUser = System.getProperty("user.name");
            File file = new File("/home/" + currentUser + "/Desktop/Json.json");
            file.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(string);
            writer.flush();
            writer.close();
            CDN.upload(file, "my0039/page/women.json");


            Message m = new Message();
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/womens", method = RequestMethod.POST)
    public String womens(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String string = CDN.download("my0039/page/women.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String chd = jsonObject.getString("women");
            String gds = null;
            JSONArray jsonArray = JSON.parseArray(chd);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                if (jsonObject1.containsKey("gds")) {
                    gds = jsonObject1.getString("gds");
                }
            }
            JSONArray jsonArray1 = JSON.parseArray(gds);
            Data data = new Data();
            data.newList();
            for (int i = 0; i < jsonArray1.size(); i++) {
                Data data1 = new Data();
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                data1.setGid(jsonObject1.getString("gid"));
                data1.setNam(jsonObject1.getString("nam"));
                data1.setPri(jsonObject1.getString("pri"));
                data1.setDis(jsonObject1.getString("dis"));
                data1.setIm1(jsonObject1.getString("im1"));
                data1.setIm2(jsonObject1.getString("im2"));
                String mes = JSON.toJSONString(data1);
                data.setDa(mes);
            }
            Message m = new Message();
            m.setData(data);
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/addWomens", method = RequestMethod.POST)
    public String addWomens(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String gid = d.getGid();
            Goods goods = goodsSever.findByGid(gid);
//        String gid=goods.getGid();
            String nam = goods.getTitle();
            String pri = goods.getPrice();
            String dis = goods.getDiscount();
            String im = goods.getIm();

            String im1 = null;
            String im2 = null;
            String[] ims = im.split("https:");
            if (ims.length > 3) {
                im1 = "https:" + ims[1];
                im2 = "https:" + ims[2];
                im1 = CDN.picture(im1, gid + "-women01", "goods");
                im2 = CDN.picture(im2, gid + "-women02", "goods");

            } else {
                im1 = "https:" + ims[1];
                im1 = CDN.picture(im1, gid + "-women01", "goods");

            }

            String string = CDN.download("my0039/page/women.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String women = jsonObject.getString("women");
            JSONArray jsonArray = JSON.parseArray(women);
            JSONArray jsonArrayGds = new JSONArray();
            String gds = null;
            for (int i = 0; i < jsonArray.size(); i++) {
                String str = jsonArray.getString(i);
                JSONObject jsonObjectI = JSON.parseObject(str);
                if (jsonObjectI.containsKey("gds")) {
                    gds = jsonObjectI.getString("gds");
                }
            }
            jsonArrayGds = JSON.parseArray(gds);

            JSONObject jsonObjectGds = new JSONObject();
            jsonObjectGds.put("gid", gid);
            jsonObjectGds.put("nam", nam);
            jsonObjectGds.put("pri", pri);
            jsonObjectGds.put("dis", dis);
            jsonObjectGds.put("im1", im1);
            jsonObjectGds.put("im2", im2);

            jsonArrayGds.add(jsonObjectGds);
            gds = jsonArrayGds.toJSONString();

            for (int i = 0; i < jsonArray.size(); i++) {
                String str = jsonArray.getString(i);
                JSONObject jsonObjectI = JSON.parseObject(str);
                if (jsonObjectI.containsKey("gds")) {
                    jsonArray.getJSONObject(i).put("gds", gds);
                }
            }
            women = jsonArray.toJSONString();
            jsonObject.put("women", women);

            string = jsonObject.toJSONString();
            String currentUser = System.getProperty("user.name");
            File file = new File("/home/" + currentUser + "/Desktop/Json.json");
            file.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(string);
            writer.flush();
            writer.close();
            CDN.upload(file, "my0039/page/women.json");
            Message m = new Message();
            m.setRs("t");
            String s = JSON.toJSONString(m);

            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/new", method = RequestMethod.POST)
    public String New(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String string = CDN.download("my0039/page/new.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String nnew = jsonObject.getString("new");
            String pic = null;
            JSONArray jsonArray = JSON.parseArray(nnew);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                if (jsonObject1.containsKey("pic")) {
                    pic = jsonObject1.getString("pic");
                }
            }
            JSONArray jsonArray1 = JSON.parseArray(pic);
            Data data = new Data();
            data.newList();
            for (int i = 0; i < jsonArray1.size(); i++) {
                Data data1 = new Data();
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                data1.setNe(jsonObject1.getString("ne"));
                data1.setIm(jsonObject1.getString("im"));
                data1.setLik(jsonObject1.getString("lik"));
                String mes = JSON.toJSONString(data1);
                data.setDa(mes);
            }
            Message m = new Message();
            m.setData(data);
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/alterNews", method = RequestMethod.POST)
    public String alterNews(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String in = d.getIn();
            String ne = d.getNe();
            String im = d.getIm();
            String lik = d.getLik();
            im = CDN.picture(im, ne, "new");
            String string = CDN.download("my0039/page/new.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String aNew = jsonObject.getString("new");
            JSONArray jsonArray = JSON.parseArray(aNew);
            for (int i = 0; i < jsonArray.size(); i++) {
                if (jsonArray.getJSONObject(i).containsKey("pic")) {
                    String pic = jsonArray.getJSONObject(i).getString("pic");
                    JSONArray jsonArray1 = JSON.parseArray(pic);

                    for (int j = 0; j < jsonArray1.size(); j++) {
                        System.out.println(jsonArray1.getJSONObject(j).getString("in"));
                        if (jsonArray1.getJSONObject(j).getString("in").equals(in)) {
                            jsonArray1.getJSONObject(j).put("ne", ne);
                            jsonArray1.getJSONObject(j).put("im", im);
                            jsonArray1.getJSONObject(j).put("lik", lik);
                        }
                    }
                    pic = jsonArray1.toJSONString();
                    jsonArray.getJSONObject(i).put("pic", pic);
                }
            }
            aNew = jsonArray.toJSONString();
            jsonObject.put("new", aNew);

            string = jsonObject.toJSONString();
            String currentUser = System.getProperty("user.name");
            File file = new File("/home/" + currentUser + "/Desktop/Json.json");
            file.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(string);
            writer.flush();
            writer.close();
            CDN.upload(file, "my0039/page/new.json");


            Message m = new Message();
            m.setRs("t");
            String s = JSON.toJSONString(m);

            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/child", method = RequestMethod.POST)
    public String child(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String string = CDN.download("my0039/page/child.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String chd = jsonObject.getString("chd");
            String pic = null;
            JSONArray jsonArray = JSON.parseArray(chd);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                if (jsonObject1.containsKey("pic")) {
                    pic = jsonObject1.getString("pic");
                }
            }
            JSONArray jsonArray1 = JSON.parseArray(pic);
            Data data = new Data();
            data.newList();
            for (int i = 0; i < jsonArray1.size(); i++) {
                Data data1 = new Data();
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                data1.setNe(jsonObject1.getString("ne"));
                data1.setIm(jsonObject1.getString("im"));
                data1.setLik(jsonObject1.getString("lik"));
                String mes = JSON.toJSONString(data1);
                data.setDa(mes);
            }
            Message m = new Message();
            m.setData(data);
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/alterChild", method = RequestMethod.POST)
    public String alterChild(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String in = d.getIn();
            String ne = d.getNe();
            String im = d.getIm();
            String lik = d.getLik();
            im = CDN.picture(im, ne, "child");
            String string = CDN.download("my0039/page/child.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String chd = jsonObject.getString("chd");
            JSONArray jsonArray = JSON.parseArray(chd);
            for (int i = 0; i < jsonArray.size(); i++) {
                if (jsonArray.getJSONObject(i).containsKey("pic")) {
                    String pic = jsonArray.getJSONObject(i).getString("pic");
                    JSONArray jsonArray1 = JSON.parseArray(pic);

                    for (int j = 0; j < jsonArray1.size(); j++) {
                        System.out.println(jsonArray1.getJSONObject(j).getString("in"));
                        if (jsonArray1.getJSONObject(j).getString("in").equals(in)) {
                            jsonArray1.getJSONObject(j).put("ne", ne);
                            jsonArray1.getJSONObject(j).put("im", im);
                            jsonArray1.getJSONObject(j).put("lik", lik);
                        }
                    }
                    pic = jsonArray1.toJSONString();
                    jsonArray.getJSONObject(i).put("pic", pic);
                }
            }
            chd = jsonArray.toJSONString();
            jsonObject.put("chd", chd);

            string = jsonObject.toJSONString();
            String currentUser = System.getProperty("user.name");
            File file = new File("/home/" + currentUser + "/Desktop/Json.json");
            file.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(string);
            writer.flush();
            writer.close();
            CDN.upload(file, "my0039/page/child.json");


            Message m = new Message();
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/childsGoods", method = RequestMethod.POST)
    public String childsGoods(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String string = CDN.download("my0039/page/child.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String chd = jsonObject.getString("chd");
            String gds = null;
            JSONArray jsonArray = JSON.parseArray(chd);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                if (jsonObject1.containsKey("gds")) {
                    gds = jsonObject1.getString("gds");
                }
            }
            JSONArray jsonArray1 = JSON.parseArray(gds);
            Data data = new Data();
            data.newList();
            for (int i = 0; i < jsonArray1.size(); i++) {
                Data data1 = new Data();
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                data1.setGid(jsonObject1.getString("gid"));
                data1.setNam(jsonObject1.getString("nam"));
                data1.setPri(jsonObject1.getString("pri"));
                data1.setDis(jsonObject1.getString("dis"));
                data1.setIm1(jsonObject1.getString("im1"));
                data1.setIm2(jsonObject1.getString("im2"));
                String mes = JSON.toJSONString(data1);
                data.setDa(mes);
            }
            Message m = new Message();
            m.setData(data);
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/addChilds", method = RequestMethod.POST)
    public String AddChilds(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String gid = d.getGid();
            Goods goods = goodsSever.findByGid(gid);
//        String gid=goods.getGid();
            String nam = goods.getTitle();
            String pri = goods.getPrice();
            String dis = goods.getDiscount();
            String im = goods.getIm();

            String im1 = null;
            String im2 = null;
            String[] ims = im.split("https:");
            if (ims.length > 3) {
                im1 = "https:" + ims[1];
                im2 = "https:" + ims[2];
                im1 = CDN.picture(im1, gid + "-child01", "goods");
                im2 = CDN.picture(im2, gid + "-child02", "goods");

            } else {
                im1 = "https:" + ims[1];
                im1 = CDN.picture(im1, gid + "-child01", "goods");

            }

            String string = CDN.download("my0039/page/child.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String child = jsonObject.getString("chd");
            JSONArray jsonArray = JSON.parseArray(child);
            JSONArray jsonArrayGds = new JSONArray();
            String gds = null;
            for (int i = 0; i < jsonArray.size(); i++) {
                String str = jsonArray.getString(i);
                JSONObject jsonObjectI = JSON.parseObject(str);
                if (jsonObjectI.containsKey("gds")) {
                    gds = jsonObjectI.getString("gds");
                }
            }
            jsonArrayGds = JSON.parseArray(gds);

            JSONObject jsonObjectGds = new JSONObject();
            jsonObjectGds.put("gid", gid);
            jsonObjectGds.put("nam", nam);
            jsonObjectGds.put("pri", pri);
            jsonObjectGds.put("dis", dis);
            jsonObjectGds.put("im1", im1);
            jsonObjectGds.put("im2", im2);

            jsonArrayGds.add(jsonObjectGds);
            gds = jsonArrayGds.toJSONString();

            for (int i = 0; i < jsonArray.size(); i++) {
                String str = jsonArray.getString(i);
                JSONObject jsonObjectI = JSON.parseObject(str);
                if (jsonObjectI.containsKey("gds")) {
                    jsonArray.getJSONObject(i).put("gds", gds);
                }
            }
            child = jsonArray.toJSONString();
            jsonObject.put("chd", child);

            string = jsonObject.toJSONString();
            String currentUser = System.getProperty("user.name");
            File file = new File("/home/" + currentUser + "/Desktop/Json.json");
            file.delete();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(string);
            writer.flush();
            writer.close();

            CDN.upload(file, "my0039/page/child.json");
            Message m = new Message();
            m.setRs("t");
            String s = JSON.toJSONString(m);

            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

    @RequestMapping(value = "/module/others", method = RequestMethod.POST)
    public String other(@RequestBody JSONObject json) throws IOException {
        Data d = JSON.parseObject(json.toString(), Data.class);
        String uuid = d.getUuid();

        Manager manager=managerServer.findByUuid(uuid);
        if (manager.getUuid().equals(uuid)) {
            String string = CDN.download("my0039/page/ot.json");
            JSONObject jsonObject = JSON.parseObject(string);
            String ot = jsonObject.getString("ot");

            JSONArray jsonArray = JSON.parseArray(ot);
            Data data = new Data();
            data.newList();
            for (int i = 0; i < jsonArray.size(); i++) {
                Data data1 = new Data();
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                data1.setNe(jsonObject1.getString("ne"));
                data1.setIm(jsonObject1.getString("im"));
                data1.setLik(jsonObject1.getString("lik"));
                String mes = JSON.toJSONString(data1);
                data.setDa(mes);
            }
            Message m = new Message();
            m.setData(data);
            m.setRs("t");
            String s = JSON.toJSONString(m);
            return s;
        }else {
            System.out.println("sorry，你无权进行此项操作！！");
            return "sorry，你无权进行此项操作！！";
        }
    }

}
