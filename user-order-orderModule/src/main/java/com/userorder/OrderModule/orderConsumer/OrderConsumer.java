package com.userorder.OrderModule.orderConsumer;

import com.rabbitmq.client.Channel;
import com.userorder.OrderModule.orderProducer.OrderProducer;
import com.userorder.OrderModule.service.OrderService;
import com.userorder.pojo.Order;
import com.userorder.pojo.OrderDetail;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderConsumer {
    @Autowired
    private OrderService orderService;
    //注入rabbitTemplage对象，用来操作rabbitmq
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderProducer orderProducer;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.custom.order.queue}"),
            exchange = @Exchange(value = "${spring.rabbitmq.custom.order.exchange}", type = "${spring.rabbitmq.custom.order.exchangeType}"),
            key = "${spring.rabbitmq.custom.order.routingKey}"
    ))
    @RabbitHandler
    public void consumeOrder(@Payload Map<String, Object> msg, Channel channel, @Headers Map<String, Object> headers) throws Exception {
        Long delivery_tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        Order order = (Order) msg.get("order");
        order.setDescription("提交到库存,等待发货");
        Integer orderId = orderService.insertOrder(order);
        List<OrderDetail> ods = (List<OrderDetail>) msg.get("ods");
        for (OrderDetail od : ods) {
            od.setOrder_id(orderId);
        }
        System.out.println("==================");
//        System.out.println("order"+order);
        System.out.println("ods" + ods);
        orderService.insertOrderDetial(ods);
        Map<Order,List<OrderDetail>> maps=new HashMap<>();
        maps.put(order, ods);
        orderProducer.sendMap(maps);
        channel.basicAck(delivery_tag, false);
    }


}
