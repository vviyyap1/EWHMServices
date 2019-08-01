package com.elasticwave.hotelmgmt.services.account.revenue.query.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenueCategoryValue {
    private String categoryId;
    private Double revenue;

    private List<RevenueCategoryValueOption> options;

}
