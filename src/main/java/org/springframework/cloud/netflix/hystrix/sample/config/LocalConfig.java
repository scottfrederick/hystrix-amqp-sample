package org.springframework.cloud.netflix.hystrix.sample.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.hystrix.amqp.HystrixConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class LocalConfig {
	@Value("${hystrix.amqp.url}")
	private String url;

	public LocalConfig() {
	}

	public LocalConfig(String url) {
		this.url = url;
	}

	@Bean
	@HystrixConnectionFactory
	public org.springframework.amqp.rabbit.connection.ConnectionFactory hystrixConnectionFactory() {
		return new CachingConnectionFactory(rabbitConnectionFactory());
	}

	@Bean
	public com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory() {
		com.rabbitmq.client.ConnectionFactory connectionFactory = new com.rabbitmq.client.ConnectionFactory();
		try {
			connectionFactory.setUri(url);
		}
		catch (Exception e) {
			throw new IllegalArgumentException("failed to create RabbitMQ ConnectionFactory", e);
		}
		return connectionFactory;
	}

}
