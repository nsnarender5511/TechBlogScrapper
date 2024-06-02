package org.narender.GraphDB;

import lombok.extern.slf4j.Slf4j;
import org.narender.GraphDB.models.Blog;
import org.narender.GraphDB.models.Category;
import org.narender.GraphDB.models.User;
import org.narender.GraphDB.service.BlogService;
import org.narender.GraphDB.service.CatogoryService;
import org.narender.GraphDB.service.UserService;
import org.narender.Web.FetchBlogController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class GraphTestArena {

    /*final String dbUri = "neo4j+s://d23347cf.databases.neo4j.io";
    final String dbUser = "neo4j";
    final String dbPassword = "ZDgGmccSfMqtEVyLFv6jJyJcCQjiqbw_AygiAsloKL0";

    @Bean
    public Configuration configuration() {
        return new Configuration.Builder()
                .uri(dbUri)
                .credentials(dbUser, dbPassword)
                .build();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new SessionFactory(configuration(), "com.example.neo4j.entity");
    }
*/
    public static void main(String[] args){
        /*final String dbUri = "neo4j+s://d23347cf.databases.neo4j.io";
        final String dbUser = "neo4j";
        final String dbPassword = "ZDgGmccSfMqtEVyLFv6jJyJcCQjiqbw_AygiAsloKL0";
*/
/*        Driver driver = GraphDatabase.driver(dbUri, AuthTokens.basic(dbUser, dbPassword));

        try (Session session = driver.session()) {
            logger.info("connected Successfully");
        }catch (Exception e){
            logger.info("can't connect to db");
        }*/
/*
        UserService userService = new UserService();
        CatogoryService catogoryService = new CatogoryService();
        BlogService blogService = new BlogService();

        User user1 = new User("User1", "1234-0", 0);
        User user2 = new User("User2", "1234-1", 0);

        User user3 = new User("User3", "1234-2", 0);


        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);

        Category category1 = new Category("Technology", 0.9);
        Category category2 = new Category("Science", 0.8);

        catogoryService.createCategory(category1);
        catogoryService.createCategory(category2);

        userService.addLikesToUser("User1", category1);
        userService.addLikesToUser("User2", category2);

        logger.info("Liked categories added to users successfully");

        Blog blog1 = new Blog("1", "Tech Trends 2024", "A look into future tech trends", "http://example.com/tech.jpg");
        Blog blog2 = new Blog("2", "Latest Scientific Discoveries", "Discoveries in science", "http://example.com/science.jpg");

        blogService.createBlog(blog1);
        blogService.createBlog(blog2);

        logger.info("Blogs created successfully");

        // Associate Blogs with Categories
        blogService.addCategoriesToBlog("1", category1);
        blogService.addCategoriesToBlog("2", category2);

        logger.info("Blogs associated with categories successfully");*/

        randomDataGeneration();








    }


    public static void randomDataGeneration(){
        UserService userService = new UserService();
        CatogoryService categoryService = new CatogoryService();
        BlogService blogService = new BlogService();

        // Generate Users
        List<User> users = new ArrayList<>();
        log.info("Starting user creation...");
        for (int i = 1; i <= 10; i++) {
            User user = new User("User" + i, "1234-" + i, 0);
            userService.createUser(user);
            users.add(user);
        }
        log.info("User creation complete.");

        // Generate Categories
        List<Category> categories = new ArrayList<>();
        log.info("Starting category creation...");

        for (int i = 1; i <= 20; i++) {
            Category category = new Category("Category" + i, 0.1 * (i % 10 + 1));
            categoryService.createCategory(category);
            categories.add(category);
        }
        log.info("Category creation complete.");

        // Generate Blogs
        List<Blog> blogs = new ArrayList<>();
        log.info("Starting blog creation...");
        for (int i = 1; i <= 20; i++) {
            Blog blog = new Blog("" + i, "Blog Title " + i, "Description for Blog " + i, "http://example.com/blog" + i + ".jpg");
            blogService.createBlog(blog);
            blogs.add(blog);
        }
        log.info("Blog creation complete.");

        // Randomly assign categories to users
        Random rand = new Random();
        log.info("Assigning categories to users...");
        for (User user : users) {
            for (int j = 0; j < 5; j++) { // Each user likes 5 categories
                Category category = categories.get(rand.nextInt(categories.size()));
                userService.addLikesToUser(user.getUsername(), category);
            }
        }
        log.info("Categories assigned to users successfully.");

        // Randomly assign categories to blogs
        log.info("Assigning categories to blogs...");
        for (Blog blog : blogs) {
            for (int j = 0; j < 3; j++) { // Each blog has 3 categories
                Category category = categories.get(rand.nextInt(categories.size()));
                blogService.addCategoriesToBlog(String.valueOf(blog.getId()), category);
                log.info("Blog " + blog.getId() + " associated with category: " + category.getName());
            }
        }
        log.info("Categories assigned to blogs successfully.");

        System.out.println("Random data generation complete.");
    }


}
