package org.narender.scrapper;

import org.narender.Objects.Blog;

import java.util.List;

public interface Scrapper {

    List<Blog> fetchLatestBlogs();
}
