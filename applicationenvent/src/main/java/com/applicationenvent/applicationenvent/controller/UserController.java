package com.applicationenvent.applicationenvent.controller;

import com.applicationenvent.applicationenvent.model.UserBean;
import com.applicationenvent.applicationenvent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService ;

    @GetMapping("/register")
    public String register(UserBean userBean){
        userService.register(userBean);
        return "注册成功" ;
    }



}
