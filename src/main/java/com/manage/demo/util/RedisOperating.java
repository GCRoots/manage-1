package com.manage.demo.util;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisOperating {
    public static void set(String key, String value) {
        Jedis jedis = RedisUtils.getJedis();
        jedis.set(key, value);
        RedisUtils.returnResource(jedis);
    }

    public static Object get(String key) {
        long t3 = System.currentTimeMillis();
        Jedis jedis = RedisUtils.getJedis();
        long t4 = System.currentTimeMillis();
        System.out.println("getJedis时间:" + (t4 - t3));

        Object value = jedis.get(key);

        RedisUtils.returnResource(jedis);
        return value;
    }

    public static long incr(String key) {
        Jedis jedis = RedisUtils.getJedis();
        long l = jedis.incr(key);
        RedisUtils.returnResource(jedis);
        return l;
    }

    public static long decr(String key) {
        Jedis jedis = RedisUtils.getJedis();
        long l = jedis.decr(key);
        RedisUtils.returnResource(jedis);
        return l;
    }

    public static void hset(String key, Map<String, String> hash) {
        Jedis jedis = RedisUtils.getJedis();
        jedis.hmset(key, hash);
        RedisUtils.returnResource(jedis);
    }

    public static Object hget(String key, String hkey) {
        Jedis jedis = RedisUtils.getJedis();
        Object s = jedis.hget(key, hkey);
        RedisUtils.returnResource(jedis);
        return s;
    }

    public static long expire(String key, int second) {
        Jedis jedis = RedisUtils.getJedis();
        long l = jedis.expire(key, second);
        RedisUtils.returnResource(jedis);
        return l;
    }

    public static Map<String, String> hgetAll(String key) {
        Jedis jedis = RedisUtils.getJedis();
        Map<String, String> map = new HashMap<>();
        map = jedis.hgetAll(key);
        RedisUtils.returnResource(jedis);
        return map;
    }

    public static long hlen(String key) {
        Jedis jedis = RedisUtils.getJedis();
        long l = jedis.hlen(key);
        RedisUtils.returnResource(jedis);
        return l;
    }

    public static void hdel(String key, String value) {
        Jedis jedis = RedisUtils.getJedis();
        jedis.hdel(key, value);
        RedisUtils.returnResource(jedis);
    }

    public static void del(String key) {
        Jedis jedis = RedisUtils.getJedis();
        jedis.del(key);
        RedisUtils.returnResource(jedis);
    }

    public static void lpus(String key, String... value) {
        Jedis jedis = RedisUtils.getJedis();
        jedis.lpush(key, value);
        RedisUtils.returnResource(jedis);
    }

    public static List<String> lrange(String key, int start, int end) {
        Jedis jedis = RedisUtils.getJedis();
        List<String> list = jedis.lrange(key, start, end);
        RedisUtils.returnResource(jedis);
        return list;
    }

    public static void lrem(String key, String value) {
        Jedis jedis = RedisUtils.getJedis();
        jedis.lrem(key, 0, value);
        RedisUtils.returnResource(jedis);
    }

    public static long llen(String key) {
        Jedis jedis = RedisUtils.getJedis();
        long size = jedis.llen(key);
        RedisUtils.returnResource(jedis);
        return size;
    }

}