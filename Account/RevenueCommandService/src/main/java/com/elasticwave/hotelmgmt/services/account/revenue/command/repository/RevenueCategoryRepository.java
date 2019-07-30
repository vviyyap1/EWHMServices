package com.elasticwave.hotelmgmt.services.account.revenue.command.repository;

import com.elasticwave.hotelmgmt.services.account.revenue.command.domain.RevenueCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RevenueCategoryRepository extends MongoRepository<RevenueCategory,Object> {

}
