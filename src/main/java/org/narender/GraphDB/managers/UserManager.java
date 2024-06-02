package org.narender.GraphDB.managers;


import lombok.extern.slf4j.Slf4j;
import org.narender.GraphDB.Neo4jConfig;
import org.narender.GraphDB.models.User;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Slf4j
public class UserManager{

    private final SessionFactory sessionFactory = Neo4jConfig.getSessonFactory();


    public void createUser(User user) {
        Session session = sessionFactory.openSession();
        User existingUser = getUserByUsername(user.getUsername());
        if (existingUser == null) {
            session.save(user);
            log.info("User created: {}", user.getUsername());
        } else {
            log.info("User already exists: {}", user.getUsername());
        }
    }

    public void updateUser(User user){
        Session session = sessionFactory.openSession();
        session.save(user);


    }

    public User getUserByUsername(String username) {
        /*try (Session session = driver.session()) {
            return session.readTransaction(tx -> {
                var result = tx.run("MATCH (u:User {username: $username}) RETURN u",
                        org.neo4j.driver.Values.parameters("username", username));
                var record = result.single();
                var node = record.get("u").asNode();
                User user = new User();
                user.setUsername(node.get("username").asString());
                user.setGuid(node.get("guid").asString());
                user.setNumberOfLikes(node.get("numberOfLikes").asInt());
                return user;
            });*/
        Session session = sessionFactory.openSession();
        User user = session.queryForObject(User.class, "MATCH (u:User {username: $username}) RETURN u",
                Map.of("username", username));
        return user;
        }

    // Other necessary methods
}
