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

	@Bean
	CommandLineRunner loadCatgories(RevenueService service, RevenueCategoryRepository categoryRepository, DailyHotelRevenueRepository dailyHotelRevenueRepository){
		return args -> {

			categoryRepository.deleteAll();

			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<RevenueCategory>> typeReference = new TypeReference<>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/static/revenuecategories.json");

			try{
				List<RevenueCategory> categories = mapper.readValue(inputStream,typeReference);
				service.saveRevenueCategories(categories);
			}catch (Exception e){
				System.out.println("Unable to Save Categories:" + e.getMessage());
			}

			dailyHotelRevenueRepository.deleteAll();
			TypeReference<List<DailyHotelRevenue>> hotelRevenueTypeReference = new TypeReference<>(){};
			inputStream = TypeReference.class.getResourceAsStream("/static/revenuecategories3.json");

			try{

				List<DailyHotelRevenue> hotelRevenues = mapper.readValue(inputStream,hotelRevenueTypeReference);
				service.saveDailyHotelRevenues(hotelRevenues);

			}catch (Exception e){
				System.out.println("Unable to Save Categories:" + e.getMessage());
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(RevenueCommandServiceApplication.class, args);
	}

}
