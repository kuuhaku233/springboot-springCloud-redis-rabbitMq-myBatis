package com.userorder.OrderModule.mapper;

import com.userorder.pojo.Order;
import com.userorder.pojo.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    Integer insertOrder(Order order);

    List<Order> selectAllByUserId(Integer userId);

    Integer insertOrderDetial(List<OrderDetail> ods);


}
