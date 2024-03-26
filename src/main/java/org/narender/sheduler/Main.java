package org.narender.sheduler;

import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.narender.Objects.Blog;

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
    private static final Logger log = LogManager.getLogger(Main.class);
    public static String url = "https://innovation.ebayinc.com/tech/archive/?section=engineering&page=1";
    public static void main( String[] args ) {

        List<Blog> blogList = new ArrayList<>();


        Document doc = null;
        Elements rows = null;
        try {
            doc = Jsoup.connect(url).get();
            rows = doc.select("div.row");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("row Size : {}",rows.size());

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
            log.info("datetime: {} ||| title: {} ||| link: {}", dateTime, titleText, href);
            log.info("-------------------------------------------------");
            blogList.add(singleBlog);
        }

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
                log.error("Erorrrr  {}",t.getMessage());

            }

    }

    public static String getSummary(Blog blog){
        String s = "";



        // Gemini Key - AIzaSyAqngWkcijMkGqRu5R5299J29rDY00G0a0


        return s;
    }
}

