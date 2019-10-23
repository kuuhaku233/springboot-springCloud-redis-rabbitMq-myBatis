package com.userorder.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Integer id;   //用户id
    private String userName;  //用户名
    private String passWord;  //用户密码
    private Double score;   //用户积分


}
