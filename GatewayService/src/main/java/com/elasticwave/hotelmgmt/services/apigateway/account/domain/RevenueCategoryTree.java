package com.elasticwave.hotelmgmt.services.apigateway.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenueCategoryTree {
    private String categoryId;
    private String name;
    private Integer order;
    private Boolean isLeaf;
    private String parentId;
    private List<RevenueCategoryOption> options;
    private List<RevenueCategoryTree> children;
}
