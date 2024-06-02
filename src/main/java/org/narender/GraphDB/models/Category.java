package org.narender.GraphDB.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@NodeEntity
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double relevancyScore;

    // Getters and Setters

    public Category(String name, double relevancyScore) {
        this.name = name;
        this.relevancyScore = relevancyScore;
    }
}
