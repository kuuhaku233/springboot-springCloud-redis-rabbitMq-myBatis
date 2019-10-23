package com.userorder.OrderModule.controller;

import com.userorder.OrderModule.orderConsumer.OrderConsumer;
import com.userorder.OrderModule.service.OrderService;
import com.userorder.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/order/all/{user_id}")
    @ResponseBody
    public List<Order> selectAllByUserId(@PathVariable("user_id") Integer user_id)
    {
        return orderService.selectAllByUserId(user_id);
    }
}
