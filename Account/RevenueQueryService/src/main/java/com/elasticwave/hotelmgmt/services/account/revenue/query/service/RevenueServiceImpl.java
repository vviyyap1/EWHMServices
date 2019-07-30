package com.elasticwave.hotelmgmt.services.account.revenue.query.service;

import com.elasticwave.hotelmgmt.services.account.revenue.query.domain.*;
import com.elasticwave.hotelmgmt.services.account.revenue.query.repository.DailyHotelRevenueRepository;
import com.elasticwave.hotelmgmt.services.account.revenue.query.repository.RevenueCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
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
    public DailyHotelRevenue getDailyRevenue(String hotelId, Date date) {
        List<DailyHotelRevenue> dailyHotelRevenues = hotelRevenueRepository.findByHotelIdAndDate(hotelId,date);
        if(dailyHotelRevenues == null)
            return null;
        List<RevenueCategory> categories = categoryRepository.findAll();
        DailyHotelRevenue dailyHotelRevenue = dailyHotelRevenues.get(0);
        dailyHotelRevenue.setCategoriesRevenueTree(RevenueService.assembleTree(categories,dailyHotelRevenue.getCategoriesRevenue(),rootCategoryId));
        return dailyHotelRevenue;
    }

    @Override
    public HotelRevenue getRevenue(String hotelId, Date from, Date to) {
        List<DailyHotelRevenue> dailyHotelRevenues = hotelRevenueRepository.findDailyHotelRevenueBetweenDates(hotelId, from, to);
        if(dailyHotelRevenues == null)
            return null;

        List<RevenueCategoryValue> aggregatedCategoryRevenue = null;
        if(!from.equals(to))
            aggregatedCategoryRevenue = aggregateDailyRevenue(dailyHotelRevenues);
        else
            aggregatedCategoryRevenue = dailyHotelRevenues.get(0).getCategoriesRevenue();

        List<RevenueCategory> categories = categoryRepository.findAll();

        RevenueCategoryValueTree aggregatedCategoryRevenueTree = RevenueService.assembleTree(categories, aggregatedCategoryRevenue, rootCategoryId);

        HotelRevenue hotelRevenue = new HotelRevenue(hotelId,from,to,dailyHotelRevenues,aggregatedCategoryRevenue, aggregatedCategoryRevenueTree);
        return hotelRevenue;
    }

    /**
     * aggregate Revenues for all categories for all days if multiple days are requested.
     * This is useful to show reports (monthly, yearly or custom time frame)
     * @param dailyHotelRevenues
     * @return
     */
    private List<RevenueCategoryValue> aggregateDailyRevenue(List<DailyHotelRevenue> dailyHotelRevenues){
        List<RevenueCategoryValue> revenueCategoryValues = dailyHotelRevenues.stream()
                .map(x->x.getCategoriesRevenue())
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
                //sum category values if there are duplicate
                .stream()
                .collect(Collectors.toMap(
                RevenueCategoryValue::getCategoryId,
                x -> new RevenueCategoryValue(x.getCategoryId(),x.getRevenue(),null),
                (a,b) -> new RevenueCategoryValue(b.getCategoryId(), a.getRevenue() + b.getRevenue(),null),
                TreeMap::new))
                .values().stream()
                        .collect(Collectors.toList());

        return revenueCategoryValues;
    }
}
