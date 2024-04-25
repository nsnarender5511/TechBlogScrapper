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

public class NetflixTechBlogScrapper implements Scrapper {
    private static final Logger logger = LoggerFactory.getLogger(NetflixTechBlogScrapper.class);

    //need To add Support for Pagination

    String url = "https://netflixtechblog.com/";

    @Override
    public List<Blog> fetchLatestBlogs() {
        List<Blog> blogList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements blogEntries = doc.select("div.js-trackPostPresentation");

            for (Element blogEntry : blogEntries) {
                Blog singleBlog = new Blog();

                // Extracting the blog URL
                String href = blogEntry.select("a").attr("href");

                // Extracting the blog title
                String titleText = blogEntry.select("h3").text();

                // Extracting the publication date
                String publicationDate = blogEntry.select("time").attr("datetime");

                singleBlog.setTitle(titleText);
                singleBlog.setUrl(href);
                singleBlog.setTimeDate(publicationDate);
                singleBlog.setCompany("Netflix Technology Blog");

                blogList.add(singleBlog);
            }
        } catch (IOException e) {
            logger.error("Error fetching blogs: ", e);
            throw new RuntimeException(e);
        }

        return blogList;
    }
}
