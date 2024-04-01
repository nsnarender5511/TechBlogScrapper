package org.narender.DOA;

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

    /**
     * Static block for initializing the EntityManagerFactory.
     * This block is executed when the class is loaded.
     */
    static {
        // "blogPU" should match the persistence unit name in your persistence.xml
        entityManagerFactory = Persistence.createEntityManagerFactory("blogPU");
    }

    /**
     * Creates a new Blog entity and persists it to the database.
     *
     * @param blog The Blog entity to be created.
     */
    public static void createBlog(Blog blog) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // Log the blog details before saving to the database
            logger.info("Saving blog: " + blog.toString());

            // Persist the passed blog entity to the database
            entityManager.persist(blog);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            logger.severe("Error occurred while creating blog: " + e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    /**
     * Additional methods for other CRUD operations can be added here.
     */

    /**
     * Closes the EntityManagerFactory when the application stops.
     */
    public static void close() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
            logger.info("EntityManagerFactory closed.");
        }
    }
}