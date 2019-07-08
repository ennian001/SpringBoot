package com.spring.cache.test2;

public class TestMain {
    public static void main(String[] args) {
        /**
         * zhsw表单 save del update
         * find toRedis toKafka
         *
         *
         */
        ZhswBiaodan zhswBiaodan = new ZhswBiaodan();
        zhswBiaodan.setNo("no1");

        ZhswOperMain zhswOperMain = new ZhswOperMain(new RedisZhswOper());
        zhswOperMain.operation(zhswBiaodan);




    }
}
