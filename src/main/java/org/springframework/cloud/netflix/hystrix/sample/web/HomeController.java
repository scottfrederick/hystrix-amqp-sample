package org.springframework.cloud.netflix.hystrix.sample.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.amqp.HystrixConnectionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@Autowired(required = false)
	private ConnectionFactory connectionFactory;

	@Autowired(required = false)
	@HystrixConnectionFactory
	private ConnectionFactory hystrixConnectionFactory;

	@RequestMapping("/")
	@HystrixCommand
	public String home() {
		return "Can you hear me now?<br>" +
				getConnectionFactoryDetails(connectionFactory, "primary") +
				getConnectionFactoryDetails(hystrixConnectionFactory, "hystrix");
	}

	private String getConnectionFactoryDetails(ConnectionFactory connectionFactory, final String type) {
		StringBuilder details = new StringBuilder("<br>" + type + " connection factory: ");
		if (connectionFactory != null) {
			details.append(connectionFactory.getHost()).append(":").append(connectionFactory.getPort());
		}
		return details.toString();
	}

}
