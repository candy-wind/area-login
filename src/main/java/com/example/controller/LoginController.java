package com.example.controller;

import com.example.service.impl.UserServiceImpl;
import com.example.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")

public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/login")
    public Result userLogin(UserInfo userInfo){
        return userService.login(userInfo);
    }


    @PostMapping("/regist")
    public Result  registered(UserInfo userInfo){
        return userService.regist(userInfo);
    }
}
