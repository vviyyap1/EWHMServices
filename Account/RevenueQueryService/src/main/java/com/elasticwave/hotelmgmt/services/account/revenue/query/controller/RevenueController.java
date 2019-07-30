package com.elasticwave.hotelmgmt.services.account.revenue.query.controller;

import com.elasticwave.hotelmgmt.services.account.revenue.query.domain.DailyHotelRevenue;
import com.elasticwave.hotelmgmt.services.account.revenue.query.domain.HotelRevenue;
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
    RevenueService service;

    @GetMapping("/dailyRevenue/{hotelId}/on/{dateStr}")
    public DailyHotelRevenue getDailyRevenue(@PathVariable String hotelId, @PathVariable String dateStr){
        try {
            Date date = new SimpleDateFormat("MM-dd-yyyy").parse(dateStr);
            return service.getDailyRevenue(hotelId, date);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/dailyRevenue/{hotelId}/from/{fromStr}/to/{toStr}")
    public HotelRevenue getHotelRevenue(@PathVariable String hotelId, @PathVariable String fromStr, @PathVariable String toStr){
        try {
            Date from = new SimpleDateFormat("MM-dd-yyyy").parse(fromStr);
            Date to = new SimpleDateFormat("MM-dd-yyyy").parse(toStr);
            return service.getRevenue(hotelId, from, to);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
