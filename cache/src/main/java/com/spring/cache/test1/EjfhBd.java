package com.spring.cache.test1;

public class EjfhBd implements Biaodan {
    @Override
    public void save(String title) {
        System.out.println("保存表单："+title);
    }
}
