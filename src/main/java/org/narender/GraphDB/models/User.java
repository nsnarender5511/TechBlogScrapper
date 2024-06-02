package org.narender.GraphDB.models;


import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@Data
@NodeEntity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String guid;
    private int numberOfLikes;

    @Relationship(type = "CONTAINS", direction = Relationship.Direction.OUTGOING)
    private Set<Category> likedCategories;

    public User(){
        likedCategories = new HashSet<>();
    }

    public User(String username, String guid, int numberOfLikes) {
        this.username = username;
        this.guid = guid;
        this.numberOfLikes = numberOfLikes;
        likedCategories = new HashSet<>();
    }

    public void addLikedCategory(Category category){
        likedCategories.add(category);
    }


}
