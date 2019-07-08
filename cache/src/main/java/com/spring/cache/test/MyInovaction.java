package com.spring.cache.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInovaction implements InvocationHandler {

    Object object;

    public MyInovaction(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始代理");
        Object invoke = method.invoke(object, args);
        System.out.println("结束代理");
        return invoke;
    }

}
