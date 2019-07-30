package com.elasticwave.hotelmgmt.services.apigateway.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueCategory {
    private String categoryId;
    private String name;
    private Integer order;
    private Boolean isLeaf;
    private String parentId;
    private List<RevenueCategoryOption> options;
    private List<RevenueCategory> children;
}
