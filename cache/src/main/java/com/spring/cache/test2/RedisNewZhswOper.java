package com.spring.cache.test2;

public class RedisNewZhswOper implements ZhswOper {
    @Override
    public void operation(ZhswBiaodan zhswBiaodan) {
        zhswBiaodan.setNo("no2");
        System.out.println("保存新redis"+zhswBiaodan);
    }
}
