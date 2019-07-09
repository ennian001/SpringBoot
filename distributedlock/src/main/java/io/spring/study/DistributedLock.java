package io.spring.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 启动类
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class DistributedLock {
    public static void main(String[] args) {
        SpringApplication.run(DistributedLock.class,args);
    }

}
