package com.elasticwave.hotelmgmt.services.apigateway.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyHotelRevenueForUI {
    private String hotelId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;
    private RevenueCategoryValueTree categoriesRevenueTree;
}
