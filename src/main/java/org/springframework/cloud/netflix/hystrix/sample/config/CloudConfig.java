package org.springframework.cloud.netflix.hystrix.sample.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.cloud.netflix.hystrix.amqp.HystrixConnectionFactory;
import org.springframework.cloud.pivotal.config.java.PivotalCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
public class CloudConfig extends PivotalCloudConfig {
	@HystrixConnectionFactory
	public ConnectionFactory hystrixConnectionFactory() {
		return connectionFactory().hystrixConnectionFactory();
	}
}
