package com.elasticwave.hotelmgmt.services.account.revenue.query.service;

import com.elasticwave.hotelmgmt.services.account.revenue.query.domain.*;

import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public interface RevenueService {

    DailyHotelRevenue getDailyRevenue(String hotelId, Date date);

    HotelRevenue getRevenue(String hotelId, Date from, Date to);

    static RevenueCategoryValueTree assembleTree(final List<RevenueCategory> categories, final List<RevenueCategoryValue> values, final String rootNodeId){
        final List<RevenueCategoryValueTree> valueTree = RevenueService.buildCategoriesValueTreeList(categories,values);

        // Save all nodes to a map
        final Map<String,RevenueCategoryValueTree> mapTmp = valueTree.stream().collect(
                Collectors.toMap(RevenueCategoryValueTree::getCategoryId, x -> x));

        // Loop and assign parent/child relationships
        valueTree.stream()
                .forEach(x->{
                    RevenueCategoryValueTree parentTree = mapTmp.get(x.getParentId());
                    if(parentTree!=null) {
                        //show aggregated revenue for parent
                        parentTree.addRevenue(mapTmp,x.getRevenue());
                        parentTree.addChild(x);
                    }
                });

        return mapTmp.get(rootNodeId);
    }

    static List<RevenueCategoryValueTree> buildCategoriesValueTreeList (final List<RevenueCategory> categories, final List<RevenueCategoryValue> values){

        //build RevenueCategoryValueTreeList
        final List<RevenueCategoryValueTree> results = categories.stream()
                .map(RevenueCategoryValueTree::new)
                .collect(Collectors.toList());

        if(values != null) {
            // Save all nodes to a map
            final Map<String, RevenueCategoryValue> mapTmp = values.stream().collect(
                    Collectors.toMap(RevenueCategoryValue::getCategoryId, x -> x));

            //loop and add saved  values
            results.stream()
                    .forEach(x -> {
                        if (mapTmp.get(x.getCategoryId()) != null)
                            x.copyRevenueCategoryValue(mapTmp.get(x.getCategoryId()));
                    });
        }
        return results;
    }

}
