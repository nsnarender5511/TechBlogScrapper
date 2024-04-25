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
public class CanvaScrapper implements Scrapper {

    // The URL of the Uber blog
    private static final String url = "https://www.canva.dev/blog/engineering/";

    // The logger for this class
    private static final Logger logger = LoggerFactory.getLogger(CanvaScrapper.class);

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
            Elements blogEntries = doc.select("div.card_card__93_is.card_large__7wYIF.blog_card_post__Bhc_k.blog_card_post-large__zqRTV");

            // Iterate over each blog entry and extract the relevant information
            for (Element blogEntry : blogEntries) {
                Blog singleBlog = new Blog();

                // Extract the href attribute of the blog entry
                String href = "https://www.canva.dev/blog/engineering/behind-the-draw/" + blogEntry.attr("href");


                // Extract the category, title, and date of the blog entry
                //String category = blogEntry.select("div.c3.hp").text();
                String title = blogEntry.select("h2.blog_card_title__wl29Z").text();
                String date = blogEntry.select("time.blog_author_date__dTF5u").text();

                // Set the extracted information in the Blog object
                singleBlog.setUrl(href);
                singleBlog.setTitle(title);
                singleBlog.setTimeDate(date);
                singleBlog.setCompany("Canva");

                // Add the Blog object to the list
                blogList.add(singleBlog);
            }
        } catch (Throwable t) {
            // Log any errors that occur during the scraping process
            logger.error("Error scraping Canva blog", t);
        }

        return blogList;
    }
}