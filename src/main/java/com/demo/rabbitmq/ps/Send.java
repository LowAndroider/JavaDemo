package com.demo.rabbitmq.ps;

import com.demo.rabbitmq.util.ConnectionUtils;
import com.demo.rabbitmq.util.Constant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare(Constant.EXCHANGE_NAME, "fanout");

        String msg = "hello ps";

        channel.basicPublish(Constant.EXCHANGE_NAME, "", null, msg.getBytes());

        channel.close();
        connection.close();
    }
}
