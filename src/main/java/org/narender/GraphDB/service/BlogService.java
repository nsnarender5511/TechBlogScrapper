package org.narender.GraphDB.service;


import org.narender.GraphDB.managers.BlogManager;
import org.narender.GraphDB.models.Blog;
import org.narender.GraphDB.models.Category;
import org.narender.GraphDB.models.Tag;

import java.util.Set;

public class BlogService {

    private BlogManager blogManager = new BlogManager();

    public void createBlog(Blog blog) {
        blogManager.createBlog(blog);
    }

    public Blog getBlogById(String blogId) {
        return blogManager.getBlogById(blogId);
    }

    public void addCategoriesToBlog(String blogId, Category category) {
        Blog blog = blogManager.getBlogById(blogId);
        if (blog != null) {
            blog.addCategory(category);
            blogManager.updateBlog(blog);
        }
    }

    public void addTagsToBlog(String blogId, Set<Tag> tags) {
        Blog blog = blogManager.getBlogById(blogId);
        if (blog != null) {
            blog.setTags(tags);
            blogManager.createBlog(blog);
        }
    }
}
