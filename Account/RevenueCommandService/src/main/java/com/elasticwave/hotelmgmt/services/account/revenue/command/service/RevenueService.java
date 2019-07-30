package com.elasticwave.hotelmgmt.services.account.revenue.command.service;


import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface RevenueService {

    public Integer saveDailyHotelRevenues(List<DailyHotelRevenue> dailyHotelRevenues);

    public Integer saveRevenueCategories(List<RevenueCategory> categories);

    static List<RevenueCategoryValue> flattenTree(final RevenueCategoryValueTree revenueCategoryValueTree){
        return revenueCategoryValueTree.flattenedLeafNodes().map(RevenueCategoryValue::new)
                .collect(Collectors.toList());
    }

}
