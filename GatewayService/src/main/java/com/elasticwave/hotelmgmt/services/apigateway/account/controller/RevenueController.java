package com.elasticwave.hotelmgmt.services.apigateway.account.controller;

import com.elasticwave.hotelmgmt.services.apigateway.account.domain.DailyHotelRevenue;
import com.elasticwave.hotelmgmt.services.apigateway.account.domain.HotelRevenue;
import com.elasticwave.hotelmgmt.services.apigateway.account.service.RevenueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/gateway/account/revenue")
public class RevenueController {
    @Autowired
    private RevenueService service;

    @GetMapping("/dailyRevenue/{hotelId}/on/{dateStr}")
    public Object getDailyHotelRevenue(@PathVariable String hotelId, @PathVariable String dateStr){
        try {
            return service.getDailyRevenue(hotelId, dateStr);
        }catch(Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/dailyRevenue/{hotelId}/from/{fromStr}/to/{toStr}")
    public Object getHotelRevenue(@PathVariable String hotelId, @PathVariable String fromStr, @PathVariable String toStr){
        try {
            return service.getRevenue(hotelId, fromStr, toStr);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/categories/{hotelId}")
    public Object getHotelRevenue(@PathVariable String hotelId){
        try {
            return service.getCatgories(hotelId);
        }catch (Exception e){
            return e.getMessage();
        }
    }



    @PostMapping("/saveDailyHotelRevenue")
    public Object saveDailyHotelRevenues(@RequestBody DailyHotelRevenue dailyHotelRevenue) {
        try {
            return service.saveDailyHotelRevenue(dailyHotelRevenue);
        }catch(Exception e){
            return e.getMessage();
        }
    }

}
