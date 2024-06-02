package org.narender.GraphDB.service;

import org.narender.GraphDB.managers.CategoryManager;
import org.narender.GraphDB.models.Category;

public class CatogoryService {
    private CategoryManager categoryManager = new CategoryManager();

    public void createCategory(Category category) {
        categoryManager.createCategory(category);
    }

    public Category getCategoryByName(String name) {
        return categoryManager.getCategoryByName(name);
    }
}
