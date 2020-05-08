package com.example.mapper;


import com.example.controller.Result;
import com.example.vo.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserInfoMapper {

    @Select("SELECT * FROM user_info WHERE username = #{username}")
    UserInfo findByUsername(@Param(value = "username") String username);

    @Select("SELECT * FROM user_info WHERE username = #{username} AND password = #{password}")
    UserInfo findByUsernameAndPassword(@Param(value = "username") String username,@Param(value = "password") String password);

    @Insert("insert into user_info values(#{id},#{username},#{password},#{create_time})")
    @Options(useGeneratedKeys = true,keyProperty = "id")

    void regist(UserInfo userInfo);
}
