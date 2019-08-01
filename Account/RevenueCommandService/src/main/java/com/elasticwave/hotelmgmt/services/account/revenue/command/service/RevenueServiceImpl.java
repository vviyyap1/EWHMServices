package com.elasticwave.hotelmgmt.services.account.revenue.command.service;

import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.*;
import com.elasticwave.hotelmgmt.services.account.revenue.command.repository.DailyHotelRevenueRepository;
import com.elasticwave.hotelmgmt.services.account.revenue.command.repository.RevenueCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueServiceImpl implements RevenueService {
    @Autowired
    private RevenueCategoryRepository categoryRepository;

    @Autowired
    private DailyHotelRevenueRepository hotelRevenueRepository;

    @Value("${revenue.ROOT_CATEGORY_ID}")
    String rootCategoryId;


    @Override
    public String saveDailyHotelRevenues(List<DailyHotelRevenue> dailyHotelRevenues) {
        dailyHotelRevenues.stream()
                .forEach(x -> {
                    if (x.getCategoriesRevenueTree() != null) {
                        x.setCategoriesRevenue(RevenueService.flattenRevenueCategoryValueTree(x.getCategoriesRevenueTree()));
                        x.setCategoriesRevenueTree(null);
                    }
                });
        hotelRevenueRepository.saveAll(dailyHotelRevenues);
        return "success";
    }

    @Override
    public String saveDailyHotelRevenues(DailyHotelRevenue dailyHotelRevenues) {
        if (dailyHotelRevenues.getCategoriesRevenueTree() != null) {
            dailyHotelRevenues.setCategoriesRevenue(RevenueService.flattenRevenueCategoryValueTree(dailyHotelRevenues.getCategoriesRevenueTree()));
            dailyHotelRevenues.setCategoriesRevenueTree(null);
        }
        hotelRevenueRepository.save(dailyHotelRevenues);
        return "success";
    }

    @Override
    public String saveRevenueCategories(List<RevenueCategory> revenueCategories) {
        categoryRepository.saveAll(revenueCategories);
        return "success";
    }

    @Override
    public String saveRevenueCategoriesTree(RevenueCategoryTree revenueCategoriesTree) {
        categoryRepository.saveAll(RevenueService.flattenCategoryTree(revenueCategoriesTree));
        return "success";
    }
}
