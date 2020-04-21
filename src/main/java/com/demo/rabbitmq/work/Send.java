package com.demo.rabbitmq.work;

import com.demo.rabbitmq.util.ConnectionUtils;
import com.demo.rabbitmq.util.Constant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.nio.charset.StandardCharsets;

public class Send {

	public static void main(String[] args) throws Exception {
		Connection connection = ConnectionUtils.getConnection();
		
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(Constant.QUEUE_NAME, false, false, false, null);
		
		for (int i=0; i < 50; i++) {
			String msg = "hello" + i;
			channel.basicPublish("", Constant.QUEUE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));
			Thread.sleep(20);
		}

		channel.close();
		connection.close();
	}
}
