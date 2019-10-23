package com.userorder.OrderModule.service.impl;

import com.userorder.OrderModule.mapper.OrderMapper;
import com.userorder.OrderModule.service.OrderService;
import com.userorder.pojo.Order;
import com.userorder.pojo.OrderDetail;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Override
    public Integer insertOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    @Override
    @Cacheable(value = "order",key = "#root.methodName")
    public List<Order> selectAllByUserId(Integer userId) {
        return orderMapper.selectAllByUserId(userId);
    }

    @Override
    public Integer insertOrderDetial(List<OrderDetail> ods) {
        return orderMapper.insertOrderDetial(ods);
    }
}
