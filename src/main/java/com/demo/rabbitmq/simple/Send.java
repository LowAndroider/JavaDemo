package com.demo.rabbitmq.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.demo.rabbitmq.util.ConnectionUtils;
import com.demo.rabbitmq.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 发送数据给rabbitmq
 * @author Djh
 */
public class Send 
{	
	private static Logger logger = LoggerFactory.getLogger(Send.class);
	
    public static void main( String[] args ) throws IOException, TimeoutException
    {
    	// 获取连接
    	Connection connection = ConnectionUtils.getConnection();
    	// 从连接中获取通道
    	Channel channel = connection.createChannel();
    	// 创建一个队列
    	channel.queueDeclare(Constant.QUEUE_NAME, false, false, false, null);
    	
    	String msg = "hello world";
    	
    	channel.basicPublish("", Constant.QUEUE_NAME, null, msg.getBytes());
    	logger.info("Message sent successfully, content is { {} }", msg);
    	
    	channel.close();
    	connection.close();
    }
}
