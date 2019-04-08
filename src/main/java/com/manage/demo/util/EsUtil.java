package com.manage.demo.util;

import org.elasticsearch.action.admin.cluster.state.ClusterStateResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


/**
 * @author ywx 2019/01/31
 */

public class EsUtil {

    public static TransportClient client;

    //  es连接
    public static void EsContronl() {

        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();





//        try {
//            client = new PreBuiltTransportClient(settings)
//                    .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
//
//
//            System.out.println("连接成功！");
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }




    }

    public static boolean indexExist(String index) {
        IndicesExistsRequest inExistsRequest = new IndicesExistsRequest(index);
        IndicesExistsResponse inExistsResponse = client.admin().indices()
                .exists(inExistsRequest).actionGet();
        return inExistsResponse.isExists();
    }

    //添加信息
    public static void addInformation(String indexName, String type,String id, List list) throws Exception {

        if (indexExist(indexName)) {
            System.out.println("请输入新的索引名");
        } else {

            IndexResponse response = client.prepareIndex(indexName,type,id)
                    .setSource(jsonBuilder()
                    .startObject()
                    .field("",list)
                    .endObject()
                    ).get();
                System.out.println(response.toString());
        }
            getDocuments(indexName);
        }

    // up
    public static void UpInformation(String indexName, String type,String id,List list) throws Exception{
        IndexRequest indexRequest = new IndexRequest(indexName,type,id)
                .source(jsonBuilder()
                        .startObject()
                        .field("", list)
                        .endObject());
        UpdateRequest updateRequest = new UpdateRequest(indexName,type,id)
                .doc(jsonBuilder()
                        .startObject()
                        .field("", "")
                        .endObject())
                .upsert(indexRequest);
        //如果不存在此文档 ，就增加 `indexRequest
        client.update(updateRequest).get();
    }

    //删除索引
    public static void deleteIndex(String index) throws Exception{

        if (indexExist(index)) {
            DeleteIndexResponse dResponse = client.admin().indices().prepareDelete(index)
                    .execute().actionGet();
            if (!dResponse.isAcknowledged()) {
                System.out.println("删除 " + index + "失败");
            }else {
                System.out.println("删除 " + index + " 成功");
            }
        } else {
            System.out.println("索引 " + index + " 不存在");
        }

    }

    //删除指定数据
    public static void deleteData(String index, String type, String id) {
        try {

            DeleteResponse response = client.prepareDelete(index, type, id).get();

            System.out.println(response.isFragment());
            System.out.println("删除指定数据成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取所有索引name
    public static void getAllIndex(){
        ClusterStateResponse response = client.admin().cluster().prepareState().execute().actionGet();
        String[] indexs=response.getState().getMetaData().getConcreteAllIndices();
        System.out.println("Index总数为: " + indexs.length);
        for (String index : indexs) {
            System.out.println("获取的Index: " + index);
        }
    }

    //获取索引下所有信息
    public static List<Map<String, Object>> getDocuments(String index) throws Exception {

        List<Map<String, Object>> mapList = new ArrayList<>();

        SearchResponse response = client.prepareSearch(index)
                .get();
        System.out.println("共匹配到:" + response.getHits().getTotalHits() + "条记录!");
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            Map<String, Object> source = hit.getSourceAsMap();
            mapList.add(source);
        }
        System.out.println(mapList);
        return mapList;
    }

    //获取指定index，type
    public static List<Map<String, Object>> getDocuments(String index, String type ) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        SearchResponse response = client.prepareSearch(index).setTypes(type).get();
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            Map<String, Object> source = hit.getSourceAsMap();
            mapList.add(source);
        }
        System.out.println(mapList);
        return mapList;
    }

//    public static void main(String[] args) throws Exception {
//        deleteIndex("goods");
//    }
}