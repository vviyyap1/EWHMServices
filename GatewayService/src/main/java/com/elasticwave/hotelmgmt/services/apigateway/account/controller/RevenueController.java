package com.elasticwave.hotelmgmt.services.apigateway.account.controller;

import com.elasticwave.hotelmgmt.services.apigateway.account.domain.DailyHotelRevenue;
import com.elasticwave.hotelmgmt.services.apigateway.account.domain.HotelRevenue;
import com.elasticwave.hotelmgmt.services.apigateway.account.domain.RevenueCategory;
import com.elasticwave.hotelmgmt.services.apigateway.account.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/gateway/account/revenue")
public class RevenueController {
    @Autowired
    RevenueService service;

    @GetMapping("/dailyRevenue/{hotelId}/on/{dateStr}")
    public DailyHotelRevenue getDailyHotelRevenue(@PathVariable String hotelId, @PathVariable String dateStr){
        return service.getDailyRevenue(hotelId,dateStr);
    }

    @GetMapping("/dailyRevenue/{hotelId}/from/{fromStr}/to/{toStr}")
    public HotelRevenue getHotelRevenue(@PathVariable String hotelId, @PathVariable String fromStr, @PathVariable String toStr){
        return service.getRevenue(hotelId,fromStr,toStr);
    }

}
