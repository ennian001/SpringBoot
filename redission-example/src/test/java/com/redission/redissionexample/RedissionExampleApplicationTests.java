package com.redission.redissionexample;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedissionExampleApplicationTests {

    @Autowired
    RedissonClient redisson ;

    @Test
    void contextLoads() {
        System.out.println(redisson);
    }

}
