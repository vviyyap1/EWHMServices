package com.elasticwave.hotelmgmt.services.apigateway.account.service;

import com.elasticwave.hotelmgmt.services.apigateway.account.domain.DailyHotelRevenue;
import com.elasticwave.hotelmgmt.services.apigateway.account.domain.HotelRevenue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public DailyHotelRevenue getDailyRevenue(String hotelId, String dateStr) {
        return restTemplate.getForObject("http://revenuequeryservice/api/account/revenue/dailyRevenue/"+hotelId+"/on/"+dateStr, DailyHotelRevenue.class);
    }

    @Override
    public HotelRevenue getRevenue(String hotelId, String fromStr, String toStr) {
        return restTemplate.getForObject("http://revenuequeryservice/api/account/revenue/dailyRevenue/"+hotelId+"/from/"+fromStr+"/to/"+toStr, HotelRevenue.class);
    }
}
