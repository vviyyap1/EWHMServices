package com.elasticwave.hotelmgmt.services.account.revenue.command.repository;

import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.DailyHotelRevenue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface DailyHotelRevenueRepository extends MongoRepository<DailyHotelRevenue,Object> {

}
