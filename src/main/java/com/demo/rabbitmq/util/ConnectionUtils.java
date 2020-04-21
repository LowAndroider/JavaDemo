package com.demo.rabbitmq.util;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * rabbitmq连接类
 * @author DJH
 */
public class ConnectionUtils {

	private final static String HOST = "192.168.3.5";

	/**
	 * 获取MQ的连接
	 * @return MQ连接
	 * @throws TimeoutException 连接超时
	 * @throws IOException 		IO异常
	 */
	public static Connection getConnection() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		// 设置服务地址
		factory.setHost(HOST);
		// 设置AMQP 5672
		factory.setPort(5672);
		// 设置虚拟host
		factory.setVirtualHost("test");
		factory.setUsername("djh");
		factory.setPassword("root");
		
		
		return factory.newConnection();
	}
}
