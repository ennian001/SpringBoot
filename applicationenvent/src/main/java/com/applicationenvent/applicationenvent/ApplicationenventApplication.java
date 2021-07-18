package com.applicationenvent.applicationenvent;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableApolloConfig
@SpringBootApplication
public class ApplicationenventApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationenventApplication.class, args);
    }

}
