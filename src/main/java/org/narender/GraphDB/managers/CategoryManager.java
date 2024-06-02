package org.narender.GraphDB.managers;
import lombok.extern.slf4j.Slf4j;
import org.narender.GraphDB.Neo4jConfig;
import org.narender.GraphDB.models.Category;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class CategoryManager {

    private final SessionFactory sessionFactory = Neo4jConfig.getSessonFactory();



    public void createCategory(Category category) {
        Session session = sessionFactory.openSession();
        Category existingCategory = getCategoryByName(category.getName());
        if (existingCategory == null) {
            session.save(category);
            log.info("Category created: {}", category.getName());
        } else {
            log.info("Category already exists: {}", category.getName());
        }
    }

    public Category getCategoryByName(String name) {
        Session session = sessionFactory.openSession();
        return session.queryForObject(Category.class, "MATCH (c:Category {name: $name}) RETURN c",
                Map.of("name", name));
    }
}
