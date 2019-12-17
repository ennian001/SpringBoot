package com.seckill.seckill.controller;


import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Random;

@Log
@Controller
@RequestMapping("/")
public class SecKillController {

    //测试一下
//    public static void main(String[] args) {
//        Jedis jedis = new Jedis("47.105.157.113");
//        System.out.println(jedis.ping());
//        jedis.close();
//    }

    @RequestMapping(value = {"/index","/"},method = RequestMethod.GET)
    public String Index(){
        return "index";
    }
    @ResponseBody
    @PostMapping("doseckill")
    public String doseckill(@RequestParam(required = false , defaultValue = "0101") String prodid){
        //随机生成客户编号
        String uid = new Random().nextInt()+"";

        Jedis jedis = new Jedis("47.105.157.113");
            // qtKey 用与商品计数
            String qtkey = "sk"+prodid+":qt";
            //用户的key， 用于判断用户是否抢过
            String userKey = "sk"+prodid+":usr";
            //判断用户是否抢过
            if (jedis.sismember(userKey,uid)){
                log.info("已经抢过了");
                jedis.close();
                return "false";
            }
        jedis.watch(qtkey);
        //获取当前数量
        int qt = Integer.valueOf(jedis.get(qtkey));
            log.info("==》当前库存数量"+qt);
            //库存不足
            if (qt<=0){
            //库存不足解锁
            jedis.unwatch();
            log.info("抢光了");
            jedis.close();
            return "false";
        }
        Transaction transaction = jedis.multi();
        transaction.decr(qtkey);
        transaction.sadd(userKey,uid);
        List<Object> list = transaction.exec();
        if (CollectionUtils.isEmpty(list)){
            log.info("对不起差一点");
        }else {
            log.info("抢到了");
        }
//        //设置用户key
//        jedis.sadd(userKey,uid);
//        //减库存
//        jedis.decr(qtkey);
        return "true";
    }

}
