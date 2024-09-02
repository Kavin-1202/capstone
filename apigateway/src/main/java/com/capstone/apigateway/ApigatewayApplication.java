package com.capstone.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.capstone.apigateway"})
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

}
