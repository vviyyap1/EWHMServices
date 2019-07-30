package com.elasticwave.hotelmgmt.services.account.revenue.query.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenueCategoryValueOption {
    private String name;
    private Double revenue;

    public RevenueCategoryValueOption(RevenueCategoryOption option){
        this.name = option.getName();
    }
}
