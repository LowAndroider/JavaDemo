package com.demo.rabbitmq.work;

import com.demo.rabbitmq.util.ConnectionUtils;
import com.demo.rabbitmq.util.Constant;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv2 {

    public final static Logger logger = LoggerFactory.getLogger(Recv1.class);

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(Constant.QUEUE_NAME, false, false, false, null);

        // 一次只分发一个
        channel.basicQos(1);

        Consumer consumer = new DefaultConsumer(channel) {
            @SneakyThrows
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                logger.info("body: {}", new String(body));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
//                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        boolean autoAck = true;
        channel.basicConsume(Constant.QUEUE_NAME, autoAck, consumer);
    }
}