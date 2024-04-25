package org.narender.scrapper.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.narender.Objects.Blog;
import org.narender.scrapper.Scrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Auth0Scapper implements Scrapper {
    private static final Logger logger = LoggerFactory.getLogger(Auth0Scapper.class);

    String url = "https://auth0.com/blog/engineering/";

    @Override
    public List<Blog> fetchLatestBlogs() {
        List<Blog> blogList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            // Adjust the selector based on the actual structure you've described
            Elements blogEntries = doc.select("li.sc-1t3ptg8-2.aql0ob-1.SwmLi.bkYRef");

            for (Element blogEntry : blogEntries) {
                Blog singleBlog = new Blog();

                // Extracting the blog URL
                String href = blogEntry.select("a").attr("href");
                // Ensure href is a full URL. If not, prepend the domain.
                if (!href.startsWith("http")) {
                    href = "https://auth0.com" + href; // Adjust the domain as necessary
                }

                // Extracting the blog title
                String titleText = blogEntry.select("h2").text();

                singleBlog.setTitle(titleText);
                singleBlog.setUrl(href);
                singleBlog.setCompany("Auth0"); // Assuming all blogs fetched from Auth0 belong to the company Auth0

                blogList.add(singleBlog);
            }
        } catch (IOException e) {
            logger.error("Error fetching blogs: ", e);
            throw new RuntimeException(e);
        }

        return blogList;
    }
}
