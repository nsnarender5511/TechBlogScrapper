package org.narender.scrapper.RSS;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

@Slf4j
public class RSSReader {

    public static void fetchBlogs(String uri, String company){
        try {
            SyndFeed feed = readFeed(new URL(uri));
            log.info("total Blogs Fetched  ::  {} ", feed.getEntries().size());
            printFeedDetails(feed, company);
        } catch (FeedException | IOException e) {
            e.printStackTrace();
        }
    }



    public static SyndFeed readFeed(URL url) throws IOException, FeedException {
        try (InputStream inputStream = url.openStream()){
             /*BufferedInputStream bis = new BufferedInputStream(inputStream);
             BufferedReader reader = new BufferedReader(new InputStreamReader(checkForBOM(bis), StandardCharsets.UTF_8))) {

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains("<!DOCTYPE")) {
                    sb.append(line);
                }
            }*/

            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            // Remove DOCTYPE manually if it exists
            String newContent = content.replaceAll("(?i)<!DOCTYPE[^>]*>", "");
            // Now parse the cleaned content
            Document doc = Jsoup.parse(newContent, "", Parser.xmlParser());



            // Use Jsoup to fix HTML and convert to well-formed XML.
             String wellFormedHtml = doc.outerHtml();

            try (InputStream is = new ByteArrayInputStream(wellFormedHtml.getBytes(StandardCharsets.UTF_8))) {
                SyndFeedInput input = new SyndFeedInput();
                return input.build(new XmlReader(is));
            }catch (Exception e){
                log.error("Errorrr  ::  {}", e.getMessage());
                throw e;
            }

        }
    }

    private static InputStream checkForBOM(InputStream bis) throws IOException {
        bis.mark(3);
        int firstByte = bis.read();
        if (firstByte != 0xEF) {
            bis.reset();
        } else {
            bis.read();
            bis.read();
        }
        return bis;
    }

    public static void printFeedDetails(SyndFeed feed, String Company) {
        for (SyndEntry entry : feed.getEntries()) {

            log.info("Title: " + entry.getTitle());
            log.info("Link: " + entry.getLink());
            log.info("Publish Date: " + entry.getPublishedDate());
            log.info("----------");
        }
    }


    public static void main(String[] args) {
        try {
            SyndFeed feed = readFeed(new URL("https://engineering.fb.com/feed"));
            printFeedDetails(feed, "META");
        } catch (FeedException | IOException e) {
            e.printStackTrace();
        }
    }
}
