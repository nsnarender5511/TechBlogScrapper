package org.narender.GraphDB.managers;

import org.narender.GraphDB.models.Tag;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TagManager {

    private final SessionFactory sessionFactory;

    @Autowired
    public TagManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createTag(Tag tag) {
        Session session = sessionFactory.openSession();
        session.save(tag);
    }

    public Tag getTagById(String tagId) {
        Session session = sessionFactory.openSession();
        return session.queryForObject(Tag.class, "MATCH (t:Tag {tagId: $tagId}) RETURN t",
                Map.of("tagId", tagId));
    }

}
