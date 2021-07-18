package com.applicationenvent.applicationenvent.event;

import com.applicationenvent.applicationenvent.model.UserBean;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * 自定义时间UserRegisterEvent继承了ApplicationEvent，
 * 继承后必须重载构造函数，构造函数的参数可以任意指定，
 *  其中source参数指的是发生事件的对象，一般我们在发布事件时使用的是this关键字代替本类对象，
 *  而user参数是我们自定义的注册用户对象，该对象可以在监听内被获取。
 */
public class UserRegisterEvent extends ApplicationEvent {

    /**
     * 注册用户对象
     */
    private UserBean user ;

    /**
     * @param source 发生事件的对象
     * @param userBean 注册用户对象
     */
    public UserRegisterEvent(Object source, UserBean userBean) {
        super(source);
        this.user = userBean ;
    }

    public UserBean getUser() {
        return user;
    }

}
