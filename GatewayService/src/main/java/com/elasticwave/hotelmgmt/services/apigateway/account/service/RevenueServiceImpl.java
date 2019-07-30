package com.elasticwave.hotelmgmt.services.apigateway.account.service;

import com.elasticwave.hotelmgmt.services.apigateway.account.domain.DailyHotelRevenue;
import com.elasticwave.hotelmgmt.services.apigateway.account.domain.HotelRevenue;
import com.elasticwave.hotelmgmt.services.apigateway.account.domain.RevenueCategory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public DailyHotelRevenue getDailyRevenue(String hotelId, String dateStr) {
        DailyHotelRevenue dailyHotelRevenue = restTemplate.getForObject("http://localhost:8081/api/account/revenue/dailyRevenue/"+hotelId+"/on/"+dateStr, DailyHotelRevenue.class);
        return dailyHotelRevenue;
    }

    @Override
    public HotelRevenue getRevenue(String hotelId, String fromStr, String toStr) {
        HotelRevenue hotelRevenue = restTemplate.getForObject("http://localhost:8081/api/account/revenue/dailyRevenue/"+hotelId+"/from/"+fromStr+"/to/"+toStr, HotelRevenue.class);
        return hotelRevenue;
    }
}
