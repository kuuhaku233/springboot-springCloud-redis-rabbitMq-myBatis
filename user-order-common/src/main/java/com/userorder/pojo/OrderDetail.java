package com.userorder.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail implements Serializable {

    private Integer id;  //商品细节 id
    private Integer order_id;  //订单号id
    private Integer product_id;  //商品id
    private Integer price;  //商品价格
    private Integer number;  //商品数量
}
