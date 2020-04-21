package com.demo.rabbitmq.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.demo.rabbitmq.util.ConnectionUtils;
import com.demo.rabbitmq.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

/**
 * 接收rabbitmq中的数据
 * @author Djh
 *
 */
public class Recv {
	
	private static Logger logger = LoggerFactory.getLogger(Recv.class);
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtils.getConnection();
		
		final Channel channel = connection.createChannel();
		System.out.println(channel.getClass().getName());
		channel.basicConsume(Constant.QUEUE_NAME, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String routingKey = envelope.getRoutingKey();
				logger.info("routingKey: {}", routingKey);
				String contentType = properties.getContentType();
				logger.info("contentType: {}", contentType);
				long deliveryTag = envelope.getDeliveryTag();
				logger.info("deliveryTag: {}", deliveryTag);
				logger.info("body: {}", new String(body));
				channel.basicAck(deliveryTag, false);
			}
		});
	}
}
