package com.study.controller;

import com.study.listener.MyApplicationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishEventController {

    //注入上下文
    @Autowired
    private ApplicationContext applicationContext;

    //发布事件接口
    @GetMapping("/publish")
    public String PublishEvent(@RequestParam("event") String event){

        applicationContext.publishEvent(new ApplicationEvent(new String(event)) {
        });

        return "OK";
    }

}
