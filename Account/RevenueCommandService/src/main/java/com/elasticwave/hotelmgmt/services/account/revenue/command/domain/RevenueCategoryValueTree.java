package com.elasticwave.hotelmgmt.services.account.revenue.command.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Stream<RevenueCategoryValueTree> flattenedLeafNodes() {
        //only collect leaf nodes that has revenue. No need to save anything else
        if(this.children != null) {
            if (this.isLeaf && this.revenue != null && !this.revenue.equals(0.0))
                return Stream.concat(Stream.of(this), children.stream().flatMap(RevenueCategoryValueTree::flattenedLeafNodes));
            else
                return children.stream().flatMap(RevenueCategoryValueTree::flattenedLeafNodes);
        }else{
            if(this.isLeaf && this.revenue != null && !this.revenue.equals(0.0))
                return Stream.of(this);
            else
                return null;
        }
    }
}
