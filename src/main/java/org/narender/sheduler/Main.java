package org.narender.sheduler;

import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.narender.Objects.Blog;
import org.narender.scrapper.Scrapper;
import org.narender.scrapper.impl.Auth0Scapper;
import org.narender.scrapper.impl.EbayScrapper;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Main 
{
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static String url = "https://innovation.ebayinc.com/tech/archive/?section=engineering&page=1";
    public static void main( String[] args ) {

        //Scrapper scrapper = new EbayScrapper();
        Scrapper scrapper2 = new Auth0Scapper();

        List<Blog> blogList = scrapper2.fetchLatestBlogs();

        blogList.forEach(blog -> {
            logger.info("datetime: {} ||| title: {} ||| link: {}", blog.getTimeDate(), blog.getTitle(), blog.getUrl());
            logger.info("-------------------------------------------------");
        });

        saveToCSV(blogList);






    }

    public static void saveToCSV(List<Blog> blogList){
        String csvFilePath = "/narender/EbayBlogs.csv";



            try(CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))){

                for(Blog blog:blogList) {
                    String[] data = {blog.getTimeDate(), blog.getTitle(), blog.getUrl()};
                    writer.writeNext(data);
                }



                }catch(Throwable t){
                logger.error("Erorrrr  {}",t.getMessage());

            }

    }

    public static String getSummary(Blog blog){
        String s = "";



        // Gemini Key - AIzaSyAqngWkcijMkGqRu5R5299J29rDY00G0a0


        return s;
    }
}

