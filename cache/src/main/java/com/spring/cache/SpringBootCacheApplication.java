package com.spring.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.spring.cache.mapper")
@SpringBootApplication
@EnableCaching
public class SpringBootCacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheApplication.class,args);
    }
}
