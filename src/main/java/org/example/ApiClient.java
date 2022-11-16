package org.example;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

public class ApiClient {
    private static Logger logger = LoggerFactory.getLogger(ApiClient.class);
    private OkHttpClient httpClient;

    public ApiClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        httpClient = builder.connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).build();
    }

    public <T> T execute(Request request, Type returnType) throws Exception {
        try {
            Call call = httpClient.newCall(request);
            Response response = call.execute();
            return handleResponse(response, returnType);
        } catch (Exception e) {
            throw e; //TODO Implement new Exception with add more details
        }
    }

    private <T> T handleResponse(Response response, Type returnType) throws Exception {
        if (response.isSuccessful()) {
            if (response.code() == 204) { //No Content
                if (response.body() != null) {
                    try {
                        response.body().close();
                    } catch (Exception e) {
                        throw e; //TODO Implement new Exception with add more details
                    }
                }
                return null;
            } else {
                return JSON.deserialize(response.body().string(), returnType);
            }
        } else {
            String respBody = null;
            if (response.body() != null) {
                try {
                    respBody = response.body().string();
                } catch (Exception e) {
                    throw e; //TODO Implement new Exception with add more details
                }
            }//TODO add exception with arguments response.code(), response.headers() respBody, response.message()
            logger.error("Response code: " + response.code() + ": " + (respBody == null ? response.message() : respBody));
            throw new Exception("Response code:" + response.code() + ": " + (respBody == null ? response.message() : respBody));
        }
    }
}
