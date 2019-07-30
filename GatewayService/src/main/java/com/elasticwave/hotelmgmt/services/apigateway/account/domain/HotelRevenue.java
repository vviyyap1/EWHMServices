package com.elasticwave.hotelmgmt.services.apigateway.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelRevenue {
    private String hotelId;
    private Date from;
    private Date to;
    private List<DailyHotelRevenue> dailyHotelRevenues;
    private List<RevenueCategoryValue> aggregatedDailyHotelRevenue;
    private RevenueCategoryValueTree categoriesRevenueTree;

}
