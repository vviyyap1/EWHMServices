package com.elasticwave.hotelmgmt.services.account.revenue.query.repository;

import com.elasticwave.hotelmgmt.services.account.revenue.query.domain.DailyHotelRevenue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface DailyHotelRevenueRepository extends MongoRepository<DailyHotelRevenue,Object> {

    public List<DailyHotelRevenue> findByHotelIdAndDate(String hotelId, Date date);

    @Query("{'hotelId' : ?0 , 'date' : {$gte : ?1 , $lte : ?2}}")
    public List<DailyHotelRevenue> findDailyHotelRevenueBetweenDates(String hotelId, Date from, Date to);
}
