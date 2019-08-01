package com.elasticwave.hotelmgmt.services.account.revenue.command.controller;

import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.DailyHotelRevenue;
import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.RevenueCategory;
import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.RevenueCategoryTree;
import com.elasticwave.hotelmgmt.services.account.revenue.command.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account/revenue")
public class RevenueController {
    @Autowired
    private RevenueService revenueService;

    @PostMapping("/saveRevenueCategories")
    Object saveRevenueCategories(@RequestBody List<RevenueCategory> categories){
        try {
            return revenueService.saveRevenueCategories(categories);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/saveRevenueCategoriesTree")
    Object saveRevenueCategoriesTree(@RequestBody RevenueCategoryTree categoriesTree){
        try {
            return revenueService.saveRevenueCategoriesTree(categoriesTree);
        }catch(Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/saveDailyHotelRevenue")
    Object saveDailyHotelRevenues(@RequestBody DailyHotelRevenue dailyHotelRevenue){
        try {
            return revenueService.saveDailyHotelRevenues(dailyHotelRevenue);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/saveMultipleDailyHotelRevenues")
    Object loadMultipleDailyHotelRevenues(@RequestBody List<DailyHotelRevenue> dailyHotelRevenue){
        try {
            return revenueService.saveDailyHotelRevenues(dailyHotelRevenue);
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
