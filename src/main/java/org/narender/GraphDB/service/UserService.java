package org.narender.GraphDB.service;


import org.narender.GraphDB.managers.UserManager;
import org.narender.GraphDB.models.Category;
import org.narender.GraphDB.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserManager userManager = new UserManager();


    public void createUser(User user) {
        userManager.createUser(user);
    }

    public User getUserByUsername(String username) {
        return userManager.getUserByUsername(username);
    }

    public void addLikesToUser(String username, Category likedCategory) {
        User user = userManager.getUserByUsername(username);
        if (user != null) {
            user.addLikedCategory(likedCategory);
            userManager.updateUser(user);
        }
    }
}

