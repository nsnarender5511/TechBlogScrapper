package org.narender.GraphDB.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
@Data
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue
    private Long id;
    private String blogId;
    private String title;
    private String shortDescription;
    private String imageUrl;

    @Relationship(type = "HAS_CATEGORY", direction = Relationship.Direction.OUTGOING)
    private Set<Category> categories;

    @Relationship(type = "HAS_TAG", direction = Relationship.Direction.OUTGOING)
    private Set<Tag> tags;


    public Blog(String blogId, String title, String shortDescription, String imageUrl) {
        this.blogId = blogId;
        this.title = title;
        this.shortDescription = shortDescription;
        this.imageUrl = imageUrl;
        this.categories = new HashSet<>();
        this.tags = new HashSet<>();
    }

    public void addCategory(Category category){
        categories.add(category);
    }
}
