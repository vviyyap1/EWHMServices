package com.elasticwave.hotelmgmt.services.apigateway.account.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenueCategoryValue {
    private String categoryId;
    private Double revenue;

    private List<RevenueCategoryValueOption> options;
}
