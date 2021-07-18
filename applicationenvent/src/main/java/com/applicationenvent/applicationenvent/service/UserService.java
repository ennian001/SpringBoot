package com.applicationenvent.applicationenvent.service;

import com.applicationenvent.applicationenvent.event.UserRegisterEvent;
import com.applicationenvent.applicationenvent.model.UserBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    /**
     * 事件发布由ApplicationContext对象管控的，
     *  发布事件前需要注入ApplicationContext对象调用publishEvent完成事件发布
     */
    @Autowired
    ApplicationContext applicationContext ;

    /**
     * 用户注册方法
     * @param userBean
     */
    public void register(UserBean userBean) {
        log.info("start =========");
        log.info("业务逻辑...");
        // 发布UserRegisterEvent事件，事件发布是由ApplicationContext对象管控的，
        applicationContext.publishEvent(new UserRegisterEvent(this,userBean));
    }
}
