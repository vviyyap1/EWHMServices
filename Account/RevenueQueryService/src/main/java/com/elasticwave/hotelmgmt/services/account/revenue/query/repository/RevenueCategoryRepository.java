package com.elasticwave.hotelmgmt.services.account.revenue.query.repository;

import com.elasticwave.hotelmgmt.services.account.revenue.query.domain.RevenueCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RevenueCategoryRepository extends MongoRepository<RevenueCategory,Object> {

}
