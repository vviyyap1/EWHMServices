package com.elasticwave.hotelmgmt.services.account.revenue.query.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueCategoryValueTree {
    private String categoryId;
    private String name;
    private Double revenue;
    private Integer order;
    private Boolean isLeaf;
    private String parentId;
    private List<RevenueCategoryValueOption> options;
    private Set<String> optionsTypeAheadSearch;
    private List<RevenueCategoryValueTree> children;

    public RevenueCategoryValueTree(RevenueCategory category){
        this.categoryId = category.getCategoryId();
        this.name = category.getName();
        this.order = category.getOrder();
        this.isLeaf = category.getIsLeaf();
        this.parentId = category.getParentId();
        this.revenue = 0.0;
        this.optionsTypeAheadSearch = setOptionsTypeAheadSearch(category.getOptions());
    }

    public void addChild(RevenueCategoryValueTree child) {
        if(this.children == null)
            this.children = new ArrayList<>();
        if (!this.children.contains(child) && child != null)
            this.children.add(child);
    }

    private Set<String> setOptionsTypeAheadSearch (List<RevenueCategoryOption> options){
        if(options == null)
            return null;

        return options.stream()
                .map(RevenueCategoryOption::getName)
                .collect(Collectors.toSet());
    }


    public void copyRevenueCategoryValue(RevenueCategoryValue revenueCategoryValue){
        this.revenue = revenueCategoryValue.getRevenue();
        if(revenueCategoryValue.getOptions() != null) {
            this.options = revenueCategoryValue.getOptions();
            Set<String> newTypeAheadOptions = revenueCategoryValue.getOptions().stream()
                    .map(RevenueCategoryValueOption::getName)
                    .collect(Collectors.toSet());
            if (optionsTypeAheadSearch == null)
                optionsTypeAheadSearch = newTypeAheadOptions;
            else
                optionsTypeAheadSearch.addAll(newTypeAheadOptions);
        }
    }

    public void addRevenue(Map<String,RevenueCategoryValueTree> mapTmp, Double revenue){
        this.setRevenue(this.getRevenue()+revenue);
        if(mapTmp.get(this.getParentId()) != null)
            mapTmp.get(this.getParentId()).addRevenue(mapTmp, revenue);

    }
}