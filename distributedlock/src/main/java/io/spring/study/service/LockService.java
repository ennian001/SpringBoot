package io.spring.study.service;

import org.springframework.stereotype.Service;

@Service
public class LockService {
    /**
     *  锁方法
     */
    public void lockTestMethod1(){
        System.out.println("方法执行");
    }
}
