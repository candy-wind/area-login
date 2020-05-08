package com.example.service.impl;

import com.example.controller.Result;
import com.example.mapper.UserInfoMapper;
import com.example.vo.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Result findByUsername(String username) {
        return null;
    }

    @Override
    public Result regist(UserInfo userInfo) {

        Result result = new Result();
        result.setSuccess(false);

        if(StringUtils.isEmpty(userInfo.getUsername()) || StringUtils.isEmpty(userInfo.getPassword())){
            result.setMsg("用户名密码不能为null,,智障了吧");
            result.setDetail(null);
        }
        result.setDetail(null);
        try {
            UserInfo existUser = userInfoMapper.findByUsername(userInfo.getUsername());
            if(existUser != null){
                //如果用户名已存在
                result.setMsg("用户名已存在");
            }else{
                userInfo.setCreate_time(new Date());
                userInfoMapper.regist(userInfo);
                //System.out.println(user.getId());
                result.setMsg("注册成功");
                result.setSuccess(true);
                result.setDetail(userInfo);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result login(UserInfo userInfo) {
        Result result = new Result();
        result.setSuccess(false);
        if(StringUtils.isEmpty(userInfo.getPassword()) || StringUtils.isEmpty(userInfo.getUsername())  ){
            result.setMsg("用户名密码不能为空 你是傻子吗 空的还点");
            return result;
        }

        result.setDetail(null);
        try {
            UserInfo loginUser = userInfoMapper.findByUsername(userInfo.getUsername());
            if(!loginUser.getPassword().equalsIgnoreCase(userInfo.getPassword())){
                result.setMsg("登陆失败，服务密码错误！！");
                result.setSuccess(false);
                result.setDetail(userInfo);
            }
            loginUser = userInfoMapper.findByUsernameAndPassword(userInfo.getUsername(),userInfo.getPassword());

            if(loginUser != null){
                //如果用户名已存在
                result.setMsg("登陆成功");
                result.setSuccess(true);
                result.setDetail(userInfo);
            }else{
                result.setMsg("登陆失败，账号或者服务密码错误");
                result.setSuccess(false);
                result.setDetail(userInfo);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}
