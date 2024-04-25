package org.narender.Utilities;

//import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class NingHttpClient {
    private static final AsyncHttpClientConfig clientConfig = new AsyncHttpClientConfig.Builder().setMaxConnections(3).build();
    private static final AsyncHttpClient asyncHttpClient = new AsyncHttpClient(clientConfig);

    private static final Logger logger = LogManager.getLogger(NingHttpClient.class);

    static Response doGetUrlRespone(){
        Response response = null;

        AsyncHttpClient.BoundRequestBuilder boundRequestBuilder = asyncHttpClient.prepareGet("https://chat.openai.com/share/db3a0fa8-7316-4a5c-b200-5beda6b49980");
        logger.info("timeout is {} ", asyncHttpClient.getConfig().getReadTimeout());

        // Add functionility for Headers And Params;

        Long beforeTime = System.currentTimeMillis();

        Future<Response> responseFuture = boundRequestBuilder.execute();

        try {
            response = responseFuture.get();
            Long afterTime = System.currentTimeMillis();
            logger.info("-- Took " + (afterTime - beforeTime) + " miliseconds");
        } catch (InterruptedException e) {
            //Add MeaningFul ErrorHandlers
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            //Add MeaningFul ErrorHandlers
            throw new RuntimeException(e);
        }

        return response;


    }

    static Response doPostUrlResponse(String url, Map<String, String> postParams, Map<String, String> headers){
        Response response = null;

        AsyncHttpClient.BoundRequestBuilder boundRequestBuilder = asyncHttpClient.preparePost(url);
        populateRequestHeaders(headers, boundRequestBuilder);
        //populate Postparams


        long before = System.currentTimeMillis();
        Future<Response> responseFuture = boundRequestBuilder.execute();

        try{
            response = responseFuture.get();
            logger.info("URL - {} | timeTaken - {} | HttpSattusCode - {}",url, System.currentTimeMillis() - before, response.getStatusCode());
        }catch (Throwable t){
            logger.error(t.getMessage());
        }
        return response;
    }

    private static void populateRequestHeaders(Map<String, String> headers, BoundRequestBuilder boundRequestBuilder){
        if(headers != null && headers.size()>0){
            Set<Map.Entry<String, String>> entrySet = headers.entrySet();
            for(Map.Entry<String, String> entry : entrySet){
                boundRequestBuilder.addHeader(entry.getKey(), entry.getValue());

            }
        }
    }
}
