package com.elasticwave.hotelmgmt.services.account.revenue.query.controller;

import com.elasticwave.hotelmgmt.services.account.revenue.query.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/account/revenue")
public class RevenueController {
    @Autowired
    private RevenueService service;

    @GetMapping("/dailyRevenue/{hotelId}/on/{dateStr}")
    public Object getDailyRevenue(@PathVariable String hotelId, @PathVariable String dateStr){
        try {
            Date date = new SimpleDateFormat("MM-dd-yyyy").parse(dateStr);
            return service.getDailyRevenue(hotelId, date);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/dailyRevenue/{hotelId}/from/{fromStr}/to/{toStr}")
    public Object getHotelRevenue(@PathVariable String hotelId, @PathVariable String fromStr, @PathVariable String toStr){
        try {
            Date from = new SimpleDateFormat("MM-dd-yyyy").parse(fromStr);
            Date to = new SimpleDateFormat("MM-dd-yyyy").parse(toStr);
            return service.getRevenue(hotelId, from, to);
        }catch(Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/categories/{hotelId}")
    public Object getCategories(@PathVariable String hotelId){
        try {
            return service.getCategories(hotelId);
        }catch(Exception e){
            return e.getMessage();
        }
    }

}
