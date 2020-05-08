package com.example.service.impl;

import com.example.controller.Result;
import com.example.vo.UserInfo;

public interface UserService {
    Result findByUsername(String username);
    Result regist(UserInfo userInfo);
    Result login(UserInfo userInfo);



}
