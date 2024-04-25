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

public class EbayScrapper implements Scrapper {

    private static final Logger logger = LoggerFactory.getLogger(Scrapper.class);

    String url = "https://innovation.ebayinc.com/tech/archive/?section=engineering&page=1";
    @Override
    public List<Blog> fetchLatestBlogs() {
        List<Blog> blogList = new ArrayList<>();


        Document doc = null;
        Elements rows = null;
        try {
            doc = Jsoup.connect(url).get();
            rows = doc.select("div.row");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("row Size : {}",rows.size());

        for(Element row:rows){

            Blog singleBlog = new Blog();

            Element timeElement = row.select("time").first();
            String dateTime = timeElement.attr("datetime");

            singleBlog.setTimeDate(dateTime);

            Element titleDiv = row.select("div.title").first();
            String titleText = titleDiv.text();
            String href = "https://innovation.ebayinc.com/" + titleDiv.select("a").attr("href");

            singleBlog.setTitle(titleText);
            singleBlog.setUrl(href);
            singleBlog.setCompany("EBAY");

            //log.info(" datetime : {}",dateTime + " |||  title  : {}", titleText + "  |||   link : {}",href);

            blogList.add(singleBlog);
        }

        return blogList;
    }
}
