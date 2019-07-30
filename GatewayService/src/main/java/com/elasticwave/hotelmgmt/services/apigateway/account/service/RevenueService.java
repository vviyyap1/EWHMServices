package com.elasticwave.hotelmgmt.services.apigateway.account.service;

import com.elasticwave.hotelmgmt.services.apigateway.account.domain.DailyHotelRevenue;
import com.elasticwave.hotelmgmt.services.apigateway.account.domain.HotelRevenue;


import java.util.Date;


public interface RevenueService {

    public DailyHotelRevenue getDailyRevenue(String hotelId, String dateStr);

    public HotelRevenue getRevenue(String hotelId, String fromStr, String toStr);
}