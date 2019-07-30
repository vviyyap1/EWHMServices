package com.elasticwave.hotelmgmt.services.account.revenue.command.service;

import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.*;
import com.elasticwave.hotelmgmt.services.account.revenue.command.repository.DailyHotelRevenueRepository;
import com.elasticwave.hotelmgmt.services.account.revenue.command.repository.RevenueCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class RevenueServiceImpl implements RevenueService {
    @Autowired
    RevenueCategoryRepository categoryRepository;

    @Autowired
    DailyHotelRevenueRepository hotelRevenueRepository;

    @Value("${revenue.ROOT_CATEGORY_ID}")
    String rootCategoryId;


    @Override
    public Integer saveDailyHotelRevenues(List<DailyHotelRevenue> dailyHotelRevenues) {
        try {
            dailyHotelRevenues.stream()
                    .forEach(x -> {
                        if (x.getCategoriesRevenueTree() != null) {
                            x.setCategoriesRevenue(RevenueService.flattenTree(x.getCategoriesRevenueTree()));
                            x.setCategoriesRevenueTree(null);
                        }
                    });
            hotelRevenueRepository.saveAll(dailyHotelRevenues);
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Integer saveRevenueCategories(List<RevenueCategory> revenueCategories) {
        try {
            categoryRepository.saveAll(revenueCategories);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
