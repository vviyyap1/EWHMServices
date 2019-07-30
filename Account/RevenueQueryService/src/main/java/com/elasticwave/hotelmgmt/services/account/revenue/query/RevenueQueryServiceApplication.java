package com.elasticwave.hotelmgmt.services.account.revenue.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class RevenueQueryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevenueQueryServiceApplication.class, args);
	}

}
