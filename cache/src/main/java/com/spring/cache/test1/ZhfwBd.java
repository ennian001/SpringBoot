package com.spring.cache.test1;

public class ZhfwBd implements Biaodan{
    @Override
    public void save(String title) {
        System.out.println("保存到redis:"+title);
    }
}
