package io.elaticsearch.application.client;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class JavaESRestClient {

   public RestClient getRestClientFactory(){
        RestClient build = RestClient.builder(
                new HttpHost("localhost", 9200, "http"),
                new HttpHost("localhost", 9201, "http")).build();
        return build;
    }

   public void restClientClose(RestClient restClient){
        try {
            restClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
