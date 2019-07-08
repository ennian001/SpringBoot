package com.spring.cache.test2;

public class SaveZhswOper implements ZhswOper{
    @Override
    public void operation(ZhswBiaodan zhswBiaodan) {
        System.out.println("保存操作:"+zhswBiaodan.toString());
    }
}
