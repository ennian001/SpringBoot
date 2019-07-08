package com.spring.cache.test1;

public class ZhswBd implements Biaodan {
    @Override
    public void save(String title) {
        System.out.println("存到kafka："+title);
    }
}
