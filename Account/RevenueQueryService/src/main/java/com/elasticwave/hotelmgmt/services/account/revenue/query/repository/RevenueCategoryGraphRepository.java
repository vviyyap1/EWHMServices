package com.elasticwave.hotelmgmt.services.account.revenue.query.repository;

import com.elasticwave.hotelmgmt.services.account.revenue.query.domain.RevenueCategory;

import java.util.List;

public interface RevenueCategoryGraphRepository {
    public List<RevenueCategory> getSubTree(String categoryId);
}
