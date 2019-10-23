package com.userorder.OrderModule.service;

import com.userorder.pojo.Order;
import com.userorder.pojo.OrderDetail;

import java.util.List;

public interface OrderService {

    Integer insertOrder(Order order);


    List<Order> selectAllByUserId(Integer userId);

    Integer insertOrderDetial(List<OrderDetail> ods);
}
