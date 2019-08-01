package com.elasticwave.hotelmgmt.services.account.revenue.command.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RevenueCategory {
    private String categoryId;
    private String name;
    private Integer order;
    private Boolean isLeaf;
    private String parentId;
    private List<RevenueCategoryOption> options;
}
