package org.narender.sheduler;

import org.narender.scrapper.RSS.RSSReader;

import java.net.URL;

public class RssScrapper {
    public static void main(String[] args){
        RSSReader.fetchBlogs("https://engineering.fb.com/2008/02/feed/", "META");

    }
}
