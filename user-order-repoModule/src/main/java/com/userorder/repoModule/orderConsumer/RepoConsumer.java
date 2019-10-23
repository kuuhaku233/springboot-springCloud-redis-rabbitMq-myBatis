package com.userorder.repoModule.orderConsumer;

import com.rabbitmq.client.Channel;
import com.userorder.pojo.Order;
import com.userorder.pojo.OrderDetail;
import com.userorder.repoModule.service.RepoService;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RepoConsumer {
    //注入rabbitTemplage对象，用来操作rabbitmq
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RepoService repoService;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.custom.order.queue}"),
            exchange = @Exchange(value = "${spring.rabbitmq.custom.order.exchange}", type = "${spring.rabbitmq.custom.order.exchangeType}"),
            key = "${spring.rabbitmq.custom.order.routingKey}"
    ))
    @RabbitHandler
    public void consumeOrder(@Payload Map<Order, List<OrderDetail>> msg, Channel channel, @Headers Map<String, Object> headers) throws Exception {
        //来自于orderModaul的消息
        Order order = null;
        List<OrderDetail> ods = null;
        for (Map.Entry<Order, List<OrderDetail>> entry : msg.entrySet()) {
             order = entry.getKey();
             ods = entry.getValue();
        }
        System.out.println("======================");
        System.out.println(order);
        System.out.println(ods);
        repoService.updateRepo(ods);
        //修改该订单  减少库存
        Long delivery_tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(delivery_tag, false);
    }


}
