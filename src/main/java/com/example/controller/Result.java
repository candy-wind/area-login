package com.example.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @auther candy_wind
 * @Date 2020/5/3
 */
@Data
@NoArgsConstructor

public class Result<T> {

    //返回信息
    private String msg;
    //数据是否正常请求
    private boolean success;
    //具体返回的数据
    private T detail;

}
