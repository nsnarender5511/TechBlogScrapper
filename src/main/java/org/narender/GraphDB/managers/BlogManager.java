package org.narender.GraphDB.managers;

import lombok.extern.slf4j.Slf4j;
import org.narender.GraphDB.Neo4jConfig;
import org.narender.GraphDB.models.Blog;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Slf4j
public class BlogManager {
    private final SessionFactory sessionFactory = Neo4jConfig.getSessonFactory();



    public void createBlog(Blog blog) {
        Session session = sessionFactory.openSession();
        Blog existingBlog = getBlogById(blog.getBlogId());
        if (existingBlog == null) {
            session.save(blog);
            log.info("Blog created: {}", blog.getTitle());
        } else {
            log.info("Blog already exists with blogId: {}", blog.getBlogId());
        }
    }

    public void updateBlog(Blog blog){
        Session session = sessionFactory.openSession();
        session.save(blog);
    }

    public Blog getBlogById(String blogId) {
        Session session = sessionFactory.openSession();
        return session.queryForObject(Blog.class, "MATCH (b:Blog {blogId: $blogId}) RETURN b",
                Map.of("blogId", blogId));
    }
}
