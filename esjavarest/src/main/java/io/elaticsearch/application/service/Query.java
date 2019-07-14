package io.elaticsearch.application.service;

import io.elaticsearch.application.client.JavaESRestClient;
import org.apache.http.HttpEntity;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Query {
    public void getQuery(){
        Request request = new Request("GET","/");
        JavaESRestClient javaESRestClient = new JavaESRestClient();
        RestClient restClient = javaESRestClient.getRestClientFactory();

        try {
            Response response = restClient.performRequest(request);
            HttpEntity entity = response.getEntity();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            javaESRestClient.restClientClose(restClient);
        }
    }
}
