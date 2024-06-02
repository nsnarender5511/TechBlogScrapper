package org.narender.sheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.narender.DOA.BlogManager;
import org.narender.Objects.Blog;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class OpenAiScheduler {
    private static final Logger logger = LogManager.getLogger(Main.class);


    public static void main(String[] args){

        List<Blog> blogList = BlogManager.getBlogList();

        blogList.forEach( blog -> logger.info( "blogUrl is ::  {}",blog.getUrl()));

        String response = callApi(blogList.get(0).getUrl());

        logger.info(response);

    }


    private static String callApi(String blogUrl) {
        String apiUrl = "http://localhost:9999/assistant";
        String jsonInputString = "{\"link\": \"" + blogUrl + "\"}";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonInputString))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            logger.error("Error calling API", e);
            return null;
        }
    }
}
