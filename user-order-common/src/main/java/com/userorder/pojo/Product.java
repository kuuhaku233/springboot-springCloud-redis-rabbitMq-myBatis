package com.userorder.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private Integer id;   //商品id
    private Integer productName;  //商品名字
    private Integer description;   //商品描述
    private Integer price;  //商品价格
    private Integer count;  //商品计数

}
