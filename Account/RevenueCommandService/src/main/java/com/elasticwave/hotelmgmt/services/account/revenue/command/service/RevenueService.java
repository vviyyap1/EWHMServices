package com.elasticwave.hotelmgmt.services.account.revenue.command.service;

import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public interface RevenueService {

    String saveDailyHotelRevenues(List<DailyHotelRevenue> dailyHotelRevenues);

    String saveDailyHotelRevenues(DailyHotelRevenue dailyHotelRevenues);

    String saveRevenueCategories(List<RevenueCategory> categories);

    String saveRevenueCategoriesTree(RevenueCategoryTree revenueCategoriesTree);

    static List<RevenueCategoryValue> flattenRevenueCategoryValueTree(final RevenueCategoryValueTree revenueCategoryValueTree){
        return revenueCategoryValueTree.flattenedLeafNodes().map( x -> {
                    RevenueCategoryValue r = new RevenueCategoryValue();
                    BeanUtils.copyProperties(x,r);
                    return r;
                 }).collect(Collectors.toList());
    }

    static List<RevenueCategory> flattenCategoryTree(final RevenueCategoryTree categoryTree){
        return categoryTree.flattenedNodes().map(x->{
                    RevenueCategory r = new RevenueCategory();
                    BeanUtils.copyProperties(x,r);
                    return r;
                }).collect(Collectors.toList());
    }

}
