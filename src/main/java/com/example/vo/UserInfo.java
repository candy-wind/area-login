package com.example.vo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String password;
    private Date create_time;


}
