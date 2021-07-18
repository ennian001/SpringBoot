package com.applicationenvent.applicationenvent.controller;

import com.applicationenvent.applicationenvent.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ConfigController {

    @Autowired
    private ConfigProperties configProperties ;
    @Value("${name}")
    private String name ;

    @Value("${rocketmq.name-server}")
    private String nameServer ;

    @GetMapping("/getRocketMq")
    private String getRocketMq(){
        return nameServer ;
    }


    @GetMapping("/name")
    public String getName(){
        return configProperties.getName();
    }

    @GetMapping("/mysql")
    public Map<Object,Object> getMysqlProperties(){

         return new HashMap<Object,Object>(){
             {
                 put("host",configProperties.getMysqlHost());
                 put("port",configProperties.getMysqlPort());
                 put("username",configProperties.getMysqlUsername());
                 put("password",configProperties.getMysqlPassword());
             }
         };
    }

}
