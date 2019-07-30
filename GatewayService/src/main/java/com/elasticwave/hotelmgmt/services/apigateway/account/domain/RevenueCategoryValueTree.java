package com.elasticwave.hotelmgmt.services.apigateway.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

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
}

