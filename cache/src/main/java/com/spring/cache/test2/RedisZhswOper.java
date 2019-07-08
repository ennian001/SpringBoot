package com.spring.cache.test2;

public class RedisZhswOper implements ZhswOper {
    @Override
    public void operation(ZhswBiaodan zhswBiaodan) {
        System.out.println("保存redis"+zhswBiaodan);
    }
}
