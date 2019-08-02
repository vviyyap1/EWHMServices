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
    private RevenueCategoryRepository categoryRepository;

    @Autowired
    private DailyHotelRevenueRepository hotelRevenueRepository;

    @Value("${revenue.ROOT_CATEGORY_ID}")
    private String rootCategoryId;

    @Override
    public DailyHotelRevenue getDailyRevenue(String hotelId, Date date) {
        List<DailyHotelRevenue> dailyHotelRevenues = hotelRevenueRepository.findByHotelIdAndDate(hotelId,date);
        List<RevenueCategory> categories = categoryRepository.findAll();
        DailyHotelRevenue dailyHotelRevenue;
        if(dailyHotelRevenues != null && dailyHotelRevenues.size()>0)
            dailyHotelRevenue = dailyHotelRevenues.get(0);
        else
            dailyHotelRevenue = new DailyHotelRevenue(hotelId,date,null,null);

        dailyHotelRevenue.setCategoriesRevenueTree(RevenueService.assembleTree(categories,dailyHotelRevenue.getCategoriesRevenue(),rootCategoryId));
        return dailyHotelRevenue;
    }

    @Override
    public HotelRevenue getRevenue(String hotelId, Date from, Date to) {
        List<DailyHotelRevenue> dailyHotelRevenues = hotelRevenueRepository.findDailyHotelRevenueBetweenDates(hotelId, from, to);
        if(dailyHotelRevenues == null || dailyHotelRevenues.size() == 0)
            dailyHotelRevenues = Arrays.asList(new DailyHotelRevenue(hotelId,null,null,null));

        List<RevenueCategoryValue> aggregatedCategoryRevenue;
        aggregatedCategoryRevenue = (dailyHotelRevenues.size() > 1) ? aggregateDailyRevenue(dailyHotelRevenues) : dailyHotelRevenues.get(0).getCategoriesRevenue();
        List<RevenueCategory> categories = categoryRepository.findAll();

        RevenueCategoryValueTree aggregatedCategoryRevenueTree = RevenueService.assembleTree(categories, aggregatedCategoryRevenue, rootCategoryId);

        return new HotelRevenue(hotelId,from,to,dailyHotelRevenues,aggregatedCategoryRevenue, aggregatedCategoryRevenueTree);
    }

    /**
     * aggregate Revenues for all categories for all days if multiple days are requested.
     * This is useful to show reports (monthly, yearly or custom time frame)
     * @param dailyHotelRevenues
     *
     * @return
     */
    private List<RevenueCategoryValue> aggregateDailyRevenue(List<DailyHotelRevenue> dailyHotelRevenues){
        if(dailyHotelRevenues == null)
                return null;
        return new ArrayList<>(dailyHotelRevenues.stream()
                .map(DailyHotelRevenue::getCategoriesRevenue)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
                //sum category values if there are duplicate
                .stream()
                .collect(Collectors.toMap(
                        RevenueCategoryValue::getCategoryId,
                        x -> new RevenueCategoryValue(x.getCategoryId(), x.getRevenue(), null),
                        (a, b) -> new RevenueCategoryValue(b.getCategoryId(), a.getRevenue() + b.getRevenue(), null),
                        TreeMap::new))
                .values());
    }
}
