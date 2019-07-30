package com.elasticwave.hotelmgmt.services.account.revenue.command.domain;

import com.elasticwave.utils.jsondeserializer.DateDeserializerDdSlashMmSlashYyyy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
public class DailyHotelRevenue {
    private String hotelId;
    @JsonDeserialize(using = DateDeserializerDdSlashMmSlashYyyy.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;
    private List<RevenueCategoryValue> categoriesRevenue;
    private RevenueCategoryValueTree categoriesRevenueTree;
}
