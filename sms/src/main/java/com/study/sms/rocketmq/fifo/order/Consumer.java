package com.study.sms.rocketmq.fifo.order;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {

    public static void main(String[] args) throws Exception {
        //1。创建消费者组Customer ，指定消费者组名
        DefaultMQPushConsumer customer = new DefaultMQPushConsumer("unique_group_name");
        //2。 指定Nameserver地址
        customer.setNamesrvAddr("192.168.72.137:9876;192.168.72.138:9876");
//        customer.setNamesrvAddr("192.168.72.137:9876");
        //3. 订阅主题topic和Tag
        customer.subscribe("OrderTopic","*");
        //4.注册消息监听器
        customer.registerMessageListener(
                (MessageListenerOrderly) (list, consumeOrderlyContext) -> {
                    for (MessageExt msg:list) {
                        System.out.println("消费消息: "+new String(msg.getBody())+"线程名称:"+Thread.currentThread().getName());
                    }
                    return ConsumeOrderlyStatus.SUCCESS;
                }
        );
        //消费者启动
        customer.start();
        System.out.println("消费者启动");
    }

}
