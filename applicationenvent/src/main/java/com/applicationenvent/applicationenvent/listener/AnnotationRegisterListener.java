package com.applicationenvent.applicationenvent.listener;

import com.applicationenvent.applicationenvent.event.UserRegisterEvent;
import com.applicationenvent.applicationenvent.model.UserBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 在Spring内部中有多种方式实现监听如：
 *  @EventListener注解、
 *  实现ApplicationListener泛型接口、
 *  实现SmartApplicationListener接口等，我们下面来讲解下这三种方式分别如何实现。
 */
@Component
@Slf4j
public class AnnotationRegisterListener {
     /**
     * 注册监听实现方法
     * 只需要让监听类被Spring管理即可，@EventListener注解会根据方法内配置的事件完成监听。
     * 接下来可启动项目来测试事件发布时是否被监听者所感知
     * @param userRegisterEvent
     */
     @EventListener
     public void register(UserRegisterEvent userRegisterEvent){
        // 获取注册用户对象
         UserBean user=userRegisterEvent.getUser();
        // 打印注册用户信息
         log.info("@EventListener注册信息，用户名："+user.getName()+"，密码："+user.getPassword());
         /**
          * 只需要让我们的监听类被Spring所管理即可，
          * 在我们用户注册监听实现方法上添加@EventListener注解，该注解会根据方法内配置的事件完成监听。下面我们启动项目来测试下我们事件发布时是否被监听者所感知。
          */
     }


}
