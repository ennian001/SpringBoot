package com.spring.cache.test2;

public class DelZhswOper implements ZhswOper {
    @Override
    public void operation(ZhswBiaodan zhswBiaodan) {
        System.out.println("删除"+zhswBiaodan);
    }
}
