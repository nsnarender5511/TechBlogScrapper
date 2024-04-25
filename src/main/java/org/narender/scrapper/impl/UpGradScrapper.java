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
public class UpGradScrapper implements Scrapper {

    // The URL of the Uber blog
    private static final String url = "https://engineering.upgrad.com/";

    // The logger for this class
    private static final Logger logger = LoggerFactory.getLogger(UpGradScrapper.class);

    /**
     * Fetches the latest blogs from the Uber blog and returns them as a list of Blog objects.
     *
     * @return A list of Blog objects representing the latest blogs.
     */
    @Override
    public List<Blog> fetchLatestBlogs() {
        List<Blog> blogList = new ArrayList<>();

        try {
            // Connect to the UpGrad blog URL and retrieve the HTML content
            Document doc = Jsoup.connect(url).get();

            // Select all the blog entries on the page
            Elements blogEntries = doc.select("div.li.l");

            // Iterate over each blog entry and extract the relevant information
            for (Element blogEntry : blogEntries) {
                Blog singleBlog = new Blog();

                // Extract the href attribute of the blog entry
                String href = "https://engineering.upgrad.com/web-performance-and-related-stories-upgrad-com-a9fb9c6bb766" + blogEntry.attr("href");


                // Extract the category, title, and date of the blog entry
                //String category = blogEntry.select("div.c3.hp").text();
                String title = blogEntry.select("h2.be.gj.mh.mi.mj.mk.ml.mm.mn.mo.mp.mq.mr.ms.mt.mu.mv.mw.mx.my.mz.na.nb.nc.nd.ne.nf.gk.gm.gn.gp.gq.bj").text();
                String date = blogEntry.select("div.pw-multi-vote-count.l.on.oo.op.oq.or.os.ot").text();

                // Set the extracted information in the Blog object
                singleBlog.setUrl(href);
                singleBlog.setTitle(title);
                singleBlog.setTimeDate(date);
                singleBlog.setCompany("UpGrad");

                // Add the Blog object to the list
                blogList.add(singleBlog);
            }
        } catch (Throwable t) {
            // Log any errors that occur during the scraping process
            logger.error("Error scraping Upgrad blog", t);
        }

        return blogList;
    }
}