package org.springframework.cloud.netflix.hystrix.sample.config;

import io.pivotal.spring.cloud.config.java.CloudConnectorsConfig;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.cloud.netflix.hystrix.amqp.HystrixConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbits")
public class CloudConfig extends CloudConnectorsConfig {
	@Bean
	@HystrixConnectionFactory
	public ConnectionFactory hystrixConnectionFactory() {
		return connectionFactory().hystrixConnectionFactory();
	}
}
