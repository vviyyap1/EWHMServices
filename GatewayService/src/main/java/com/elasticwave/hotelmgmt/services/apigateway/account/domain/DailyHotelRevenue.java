package com.elasticwave.hotelmgmt.services.apigateway.account.domain;

import com.elasticwave.hotelmgmt.services.apigateway.account.util.DateDeserializerDd_Mm_Yyyy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyHotelRevenue {
    private String hotelId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;
    private List<RevenueCategoryValue> categoriesRevenue;
    private RevenueCategoryValueTree categoriesRevenueTree;
}
