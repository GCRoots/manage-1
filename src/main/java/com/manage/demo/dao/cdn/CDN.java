package com.manage.demo.dao.cdn;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.manage.demo.server.UserServer;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class CDN {




    public static String download(String objectName) throws IOException {
        String jsonstring=null;
        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
        String accessKeyId = "LTAIQjAn1lv7MYo5";
        String accessKeySecret = "rNGaaLAuRGFaeEvkGgHbL3fhhHaHeh";
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String currentUser = System.getProperty("user.name");
        File file=new File("/home/"+currentUser+"/Desktop/Json.json");
        ossClient.getObject(new GetObjectRequest("superljc",objectName ), file);
        ossClient.shutdown();
        jsonstring= FileUtils.readFileToString(file, "UTF-8");
        return jsonstring;
    }

    public static String picture(String im,String name,String category) throws FileNotFoundException {
        String url = null;
        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
        String accessKeyId = "LTAIQjAn1lv7MYo5";
        String accessKeySecret = "rNGaaLAuRGFaeEvkGgHbL3fhhHaHeh";
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String currentUser = System.getProperty("user.name");
        String path="/home/"+currentUser+"/Desktop/"+name+".jpg";
        downloadPicture(im,path);
        InputStream inputStream = new FileInputStream(path);
        File file=new File(path);
        file.delete();
        ossClient.putObject("superljc", "my0039/pict/page/"+category+"/"+name+".jpg", inputStream);
        Date date=new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        url=ossClient.generatePresignedUrl("superljc","my0039/pict/page/"+category+"/"+name+".jpg",date).toString();
        ossClient.shutdown();


        return url;
    }

    public static void upload(File file,String objectName){
        String endpoint = "http://oss-cn-shenzhen.aliyuncs.com";
        String accessKeyId = "LTAIQjAn1lv7MYo5";
        String accessKeySecret = "rNGaaLAuRGFaeEvkGgHbL3fhhHaHeh";
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject("superljc", objectName, file);
        ossClient.shutdown();

    }

    private static void downloadPicture(String urlList,String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
