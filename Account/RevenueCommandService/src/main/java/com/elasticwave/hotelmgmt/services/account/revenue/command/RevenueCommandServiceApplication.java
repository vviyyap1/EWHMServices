package com.elasticwave.hotelmgmt.services.account.revenue.command;

import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.DailyHotelRevenue;
import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.RevenueCategory;
import com.elasticwave.hotelmgmt.services.account.revenue.command.repository.DailyHotelRevenueRepository;
import com.elasticwave.hotelmgmt.services.account.revenue.command.repository.RevenueCategoryRepository;
import com.elasticwave.hotelmgmt.services.account.revenue.command.service.RevenueService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class RevenueCommandServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(RevenueCommandServiceApplication.class, args);
	}

}
