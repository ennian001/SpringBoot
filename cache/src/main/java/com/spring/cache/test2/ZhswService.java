package com.spring.cache.test2;

public interface ZhswService {
    public void save(ZhswBiaodan obj) ;

    public void upd(ZhswBiaodan obj);

    public void del(ZhswBiaodan obj);

    public void toRedis();
}