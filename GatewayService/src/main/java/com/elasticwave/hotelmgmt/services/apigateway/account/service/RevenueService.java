package com.elasticwave.hotelmgmt.services.apigateway.account.service;

import com.elasticwave.hotelmgmt.services.apigateway.account.domain.DailyHotelRevenue;
import com.elasticwave.hotelmgmt.services.apigateway.account.domain.HotelRevenue;

public interface RevenueService {

    DailyHotelRevenue getDailyRevenue(String hotelId, String dateStr);

    HotelRevenue getRevenue(String hotelId, String fromStr, String toStr);

    String saveDailyHotelRevenue(DailyHotelRevenue dailyHotelRevenue);
}