package org.narender.sheduler;

import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.narender.DOA.BlogManager;
import org.narender.DOA.BlogManagerWithJDBC;
import org.narender.Objects.Blog;
import org.narender.scrapper.Scrapper;
import org.narender.scrapper.impl.*;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
        //Scrapper scrapper2 = new Auth0Scapper();
        //Scrapper scrapper3 = new NetflixTechBlogScrapper();

        Scrapper scrapper4 = new PhonepeScrapper();

        Scrapper scrapper5 = new UberScrapper();


        List<Blog> blogList = scrapper5.fetchLatestBlogs();


        blogList.forEach(blog -> {
            //BlogManager.createBlog(blog);
            long startTime = System.currentTimeMillis();
            //BlogManagerWithJDBC.insert(blog);
            if(validateBlog(blog)){
                BlogManager.createBlog(blog);
                long endTime = System.currentTimeMillis();
                logger.info("Time taken to insert blog: " + (endTime - startTime) + "ms");
            }
        });

       // logger.info("Total blogs inserted: " + count);

        saveToCSV(blogList);






    }

    public static boolean validateBlog(Blog blog) {

        if(blog.getTitle() == null || blog.getTitle().isEmpty()){
            logger.error("---------------Blog title is null ------------------- ");
            return false;
        }

        if(blog.getTimeDate() == null || blog.getTimeDate().isEmpty()){
            logger.error("---------------Blog timeDate is null ------------------- ");
            return false;
        }

        if(blog.getUrl() == null || blog.getUrl().isEmpty()){
            logger.error("---------------Blog url is null ------------------- ");
            return false;
        }

        if(blog.getCompany() == null || blog.getCompany().isEmpty()){
            logger.error("---------------Blog company is null ------------------- ");
            return false;
        }

        return true;
    }

    public static void saveToCSV(List<Blog> blogList){
        String csvFilePath = "/narender/UberBlog.csv";



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

        List<Integer> list = new ArrayList<>();



        // Gemini Key - AIzaSyAqngWkcijMkGqRu5R5299J29rDY00G0a0


        return s;
    }
}

