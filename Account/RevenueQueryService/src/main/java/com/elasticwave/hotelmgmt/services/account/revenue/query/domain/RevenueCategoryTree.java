package com.elasticwave.hotelmgmt.services.account.revenue.query.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    public void addChild(RevenueCategoryTree child) {
        if(this.children == null)
            this.children = new ArrayList<>();
        if (!this.children.contains(child) && child != null)
            this.children.add(child);
    }
}
