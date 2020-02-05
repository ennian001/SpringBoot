package com.spring.cache;

import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {

    static Jedis jedis = new Jedis("192.168.116.128", 6379);

    final static String KEY = "company";
    final static String MEMBER_JUEJIN = "juejin";
    final static String MEMBER_MEITUAN = "meituan";
    final static String MEMBER_IREADER = "ireader";
    final static String MEMBER_JINGDONG = "jd";
    final static String MEMBER_XIAOMI = "xiaomi";
    public static void main(String[] args) {
        geoRadiusByMember();
    }

    //获取附近的公司
    public static void geoRadiusByMember(){
        List<GeoRadiusResponse> geoRadiusResponses = jedis.georadiusByMember(KEY, MEMBER_IREADER, 20, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().count(3).sortDescending());

        geoRadiusResponses.forEach(
                (e)->{
                    System.out.println(e.getMemberByString());
                }
        );

    }

    //获取元素中任意元素的经纬坐标\Base32编码
    public static void get_Longitude_Latitude(){
        //一次获取一个
        List<GeoCoordinate> geopos = jedis.geopos(KEY, MEMBER_JUEJIN);
        //一次获取多个
        List<GeoCoordinate> geoposManay = jedis.geopos(KEY, MEMBER_JUEJIN, MEMBER_IREADER);

        GeoCoordinate geoCoordinate = geopos.get(0);
        //经度
        System.out.println("经度:"+geoCoordinate.getLongitude());
//        维度
        System.out.println("纬度:"+geoCoordinate.getLatitude());

        List<String> geohash = jedis.geohash(KEY, MEMBER_JUEJIN);
        System.out.println(geohash.get(0));
    }
    //获取目标距离
    private static void getDistinct(){
        Jedis jedis = new Jedis("192.168.116.128", 6379);
        Double geodist = jedis.geodist(KEY, MEMBER_JUEJIN, MEMBER_MEITUAN, GeoUnit.KM);//距离单位可以是 m、km、ml\英尺 等
        System.out.println(geodist);
    }
    //添加目标地址 GeoHash算法
    private static void geoHash(){
        Jedis jedis = new Jedis("192.168.116.128", 6379);
        //添加地图坐标
        //加入公司三元组
        jedis.geoadd("company",116.48105,39.996794,"juejin");
        jedis.geoadd("company",116.514203,39.905409,"ireader");
        jedis.geoadd("company",116.489033,40.007669,"meituan");
        jedis.geoadd("company",116.562108,39.787602,"jd");
        jedis.geoadd("company",116.334255,40.027400,"xiaomi");
    }

    private static void getKey() {
        //        Jedis jedis = new Jedis("47.105.157.113", 6379);
        Jedis jedis = new Jedis("192.168.116.128", 6379);
        System.out.println("connection is OK ==========>"+jedis.ping());
        //key
        Set<String> keys = jedis.keys("*");
        for (String key:keys) {
            System.out.println(key);
        }
        System.out.println("jedis.exists:"+jedis.exists("k2"));
        System.out.println(jedis.ttl("k1"));
        //hash
        jedis.hset("hash1","userName","lisi");
        System.out.println(jedis.hget("hash1","userName"));
        Map<String,String> map = new HashMap<>();
        map.put("telphone","123456789");
        map.put("address","beijing");
        map.put("email","abc@123.com");
        jedis.hmset("hash2",map);
        List<String> result = jedis.hmget("hash2","telphone","email");
        for (String element: result) {
            System.out.println(element);
        }
        System.out.println("-----------------------------------------");
    }
}
