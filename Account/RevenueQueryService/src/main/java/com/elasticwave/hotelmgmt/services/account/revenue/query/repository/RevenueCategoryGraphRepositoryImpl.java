package com.elasticwave.hotelmgmt.services.account.revenue.query.repository;

import com.elasticwave.hotelmgmt.services.account.revenue.query.domain.RevenueCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GraphLookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

public class RevenueCategoryGraphRepositoryImpl implements RevenueCategoryGraphRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    //not working yet. Do we need to show subTree or shall we always get the full tree?
    public List<RevenueCategory> getSubTree(String categoryId) {

        final Criteria byCategoryId = new Criteria("categoryId").is(categoryId);
        final MatchOperation matchStage = Aggregation.match(byCategoryId);

        GraphLookupOperation graphLookupOperation = GraphLookupOperation.builder()
                .from("RevenueCategory")
                .startWith("$categoryId")
                .connectFrom("categoryId")
                .connectTo("parentId")
                .as("children");

        Aggregation aggregation = Aggregation.newAggregation(matchStage,graphLookupOperation);
        //Aggregation aggregation =  Aggregation.newAggregation(graphLookupOperation);
        List<RevenueCategory> results = mongoTemplate.aggregate(aggregation, "RevenueCategory", RevenueCategory.class).getMappedResults();
        return results;
    }
}
