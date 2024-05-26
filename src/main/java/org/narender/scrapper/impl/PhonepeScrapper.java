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

public class PhonepeScrapper implements Scrapper{
    private static final Logger logger = LoggerFactory.getLogger(PhonepeScrapper.class);

    String url = "https://tech.phonepe.com"; // Replace with the actual URL

    @Override
    public List<Blog> fetchLatestBlogs() {
        List<Blog> blogList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements articles = doc.select("li > article.post-list-item");

            for (Element article : articles) {
                Blog singleBlog = new Blog();

                // Extracting the blog URL
                String href = article.select("h1 > a").attr("href");

                // Extracting the blog title
                String title = article.select("h1 > a > span[itemprop=headline]").text();

                ///Extracting the authors and publication date
               // String authors = article.select("h2.subtitle > small").first().text();
                //String publicationDate = article.select("h2.subtitle > small").last().text();

                String publicationDate = article.select("h2.subtitle > small").last().text();

                singleBlog.setTitle(title);
                singleBlog.setUrl(url+href);
                singleBlog.setTimeDate(publicationDate);
                singleBlog.setCompany("Phonepe");
                //singleBlog.setAuthors(authors); // Assuming Blog has a method setAuthors
                //singleBlog.setPublicationDate(publicationDate); // Assuming Blog has a method setPublicationDate

                blogList.add(singleBlog);
            }
        } catch (IOException e) {
            logger.error("Error fetching blogs: ", e);
            throw new RuntimeException(e);
        }

        return blogList;
    }
}
