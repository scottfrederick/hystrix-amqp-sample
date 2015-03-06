package org.springframework.cloud.netflix.hystrix.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class HystrixSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixSampleApplication.class, args);
    }
}
