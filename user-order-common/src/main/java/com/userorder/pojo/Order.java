package com.userorder.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private Integer id;   //订单id
    private String address;  //收货人地址
    private Date createTime;  //订单生成时间
    private String receiveName;  //收货人姓名
    private String phone;  //收货人电话
    private Integer user_id;  //用户id
    private Double totalPrice; //商品总价
    private Integer status;   //商品状态
    private String description;  //商品描述


}
