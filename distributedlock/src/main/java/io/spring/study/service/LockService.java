package io.spring.study.service;

import io.spring.study.annotation.SynLock;
import org.springframework.stereotype.Service;

@Service
public class LockService {



    /**
     *  锁方法
     */
    @SynLock(synKey = "lock",seconds = 40)
    public void lockTestMethod1(){
        System.out.println("方法执行");
    }
}
