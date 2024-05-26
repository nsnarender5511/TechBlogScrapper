package org.narender.scrapper.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.narender.Objects.Blog;
import org.narender.scrapper.Scrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that implements the Scrapper interface and provides functionality to scrape blog data from Uber's website.
 */
public class UberScrapper implements Scrapper {

    // The URL of the Uber blog
    private static final String url = "https://blog.uber.com/";

    // The logger for this class
    private static final Logger logger = LoggerFactory.getLogger(UberScrapper.class);

    /**
     * Fetches the latest blogs from the Uber blog and returns them as a list of Blog objects.
     *
     * @return A list of Blog objects representing the latest blogs.
     */
    @Override
    public List<Blog> fetchLatestBlogs() {
        List<Blog> blogList = new ArrayList<>();

        try {
            // Connect to the Uber blog URL and retrieve the HTML content
            Document doc = Jsoup.connect(url).get();

            // Select all the blog entries on the page
            Elements blogEntries = doc.select("a[data-baseweb=card]");

            // Iterate over each blog entry and extract the relevant information
            for (Element blogEntry : blogEntries) {
                Blog singleBlog = new Blog();

                // Extract the href attribute of the blog entry
                String href = "https://www.uber.com/en-IN/blog/" + blogEntry.attr("href");


                // Extract the category, title, and date of the blog entry
                //String category = blogEntry.select("div.c3.hp").text();
                String title = blogEntry.select("h5.ca").text();
                String date = blogEntry.select("p.c3").text();

                String imageUrl = blogEntry.select("img").attr("src");

                // Set the extracted information in the Blog object
                singleBlog.setUrl(href);
                singleBlog.setTitle(title);
                singleBlog.setTimeDate(date);
                singleBlog.setCompany("Uber");

                // Add the Blog object to the list
                blogList.add(singleBlog);
            }
        } catch (Throwable t) {
            // Log any errors that occur during the scraping process
            logger.error("Error scraping Uber blog", t);
        }

        return blogList;
    }
}