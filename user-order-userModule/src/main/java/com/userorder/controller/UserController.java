package com.userorder.controller;

import com.userorder.pojo.Order;
import com.userorder.pojo.User;
import com.userorder.sendOrder.UserProducer;
import com.userorder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserProducer userProducer;

    @GetMapping("/select/{id}")
    @ResponseBody
    public User selectUser(@PathVariable(value = "id") Integer id, HttpSession session)
    {

        User user = userService.selectUser(id);
        session.setAttribute("user", user);
        return user;
    }

    @GetMapping("/addorder")
    @ResponseBody
    public String addOrder(Integer[] ids, Integer[] nums, HttpSession session, Order order)
    {
        User user = getUser(session);
        order.setUser_id(user.getId());
        //创建商品详情Map，发送到消息队列上
        Map<Integer,Integer> details = new HashMap<>();
        for (int i = 0; i < ids.length; i++) {
            details.put(ids[i],nums[i]);
        }
        this.userProducer.sendMap(details,order);
        return "订单消息发送成功";
    }
    private User getUser(HttpSession session)
    {
        User user = (User) session.getAttribute("user");
        return user;
    }

}
