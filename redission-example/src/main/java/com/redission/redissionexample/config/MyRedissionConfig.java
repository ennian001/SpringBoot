package com.redission.redissionexample.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRedissionConfig {
    /**
     * 所有对Redisson的使用都是通过
     * RedissonClient
     * @return
     */
    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson(){
        Config config = new Config();
        //集群模式
//        config.useClusterServers()
//                .addNodeAddress("","");
        config.useSingleServer().
                setAddress("redis://ip:port")
        .setDatabase(1).
                setPassword("c1b2m3");
        ;
        return Redisson.create(config);
    }
}
