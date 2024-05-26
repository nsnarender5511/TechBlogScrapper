package org.narender.DOA;

import jakarta.persistence.EntityTransaction;
import org.narender.Objects.Blog;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.logging.Logger;

/**
 * Manager class for managing Blog entities.
 */
public class BlogManager {
    private static final Logger logger = Logger.getLogger(BlogManager.class.getName());
    private static EntityManagerFactory entityManagerFactory;
    static {
        // "blogPU" should match the persistence unit name in your persistence.xml
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
    }

    public static void createBlog(Blog blog) {
        //entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {

            EntityTransaction txn = entityManager.getTransaction();
            txn.begin();

            // Log the blog details before saving to the database
            logger.info("Saving blog: " + blog.toString());

            // Persist the passed blog entity to the database
            entityManager.persist(blog);

            txn.commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.severe("Error occurred while creating blog: " + e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    public static void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
            logger.info("EntityManagerFactory closed.");
        }
    }
}