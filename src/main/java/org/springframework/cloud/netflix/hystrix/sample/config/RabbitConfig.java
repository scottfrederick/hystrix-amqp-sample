package org.springframework.cloud.netflix.hystrix.sample.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("rabbits")
public class RabbitConfig extends AbstractCloudConfig {
	public static final String RABBIT_CONNECTION_FACTORY = "rabbitConnectionFactory";
	public static final String SAMPLE_EXCHANGE = "sample-exchange";
	public static final String SAMPLE_QUEUE = "sample-queue";

	@Bean(name = RABBIT_CONNECTION_FACTORY)
	@Primary
	public ConnectionFactory rabbitConnectionFactory() {
		ConnectionFactory connectionFactory = connectionFactory().rabbitConnectionFactory();
		return connectionFactory;
	}

	@Bean
	public DirectExchange sampleExchange() {
		return new DirectExchange(SAMPLE_EXCHANGE, true, false);
	}

	@Bean
	public Queue sampleQueue() {
		return new Queue(SAMPLE_QUEUE);
	}

	@Bean
	public Binding sampleBinding() {
		Queue sampleQueue = sampleQueue();
		DirectExchange sampleExchange = sampleExchange();
		return BindingBuilder.bind(sampleQueue).to(sampleExchange).with(sampleQueue.getName());
	}
}
