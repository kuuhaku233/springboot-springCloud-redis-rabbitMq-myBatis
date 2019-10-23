package com.userorder.sendOrder;

import com.userorder.pojo.Order;
import com.userorder.pojo.OrderDetail;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserProducer {

    //注入rabbitTemplage对象，用来操作rabbitmq
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.out.println("UserProducer ConfirmCallback+++++++++++++++++++++");
            System.out.println(correlationData.getId());
            //假如exchange返回的是ack是false，打印错误消息
            if (!ack) {
                System.out.println("错误原因是：" + cause);
            }
        }
    };

    private RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message, int errorCode, String errorCause, String exchangeName, String routingKey) {
            System.out.println("UserProducer returnCallBack==========================");
            System.out.println(message);
            System.out.println(errorCode);
            System.out.println(errorCause);
            System.out.println(exchangeName);
            System.out.println(routingKey);
        }
    };


    public void sendMap(Map<Integer, Integer> maps, Order order) {
        //消息的唯一ID
        CorrelationData correlationDate = new CorrelationData(UUID.randomUUID().toString());
        //给消息生产者设置消息确认、返回的监听

        //封装剩余的order 对象信息
        order.setCreateTime(new Date());
        order.setStatus(0);
        List<OrderDetail> ods = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : maps.entrySet()) {
            Integer key = entry.getKey();
            Integer value = +entry.getValue();
            OrderDetail od = new OrderDetail();
            od.setProduct_id(key);
            od.setNumber(value);
            ods.add(od);
        }
        Map<String,Object> msg=new HashMap<>();
        msg.put("order", order);
        msg.put("ods", ods);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.convertAndSend("user_order_exchange", "user_order.create", msg, correlationDate);

    }


}
