package com.elasticwave.hotelmgmt.services.account.revenue.query.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelRevenue {
    private String hotelId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date from;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date to;
    private List<DailyHotelRevenue> dailyHotelRevenues;
    private List<RevenueCategoryValue> aggregatedDailyHotelRevenue;
    private RevenueCategoryValueTree categoriesRevenueTree;

}
