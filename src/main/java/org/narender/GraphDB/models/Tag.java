package org.narender.GraphDB.models;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    private String tagId;
    private String name;
    private double relevancyScore;

    // Getters and Setters
}
