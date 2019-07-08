package com.spring.cache.test2;

public class UpdZhswOper implements ZhswOper {
    @Override
    public void operation(ZhswBiaodan zhswBiaodan) {
        System.out.println("更新操作"+zhswBiaodan);
    }
}
