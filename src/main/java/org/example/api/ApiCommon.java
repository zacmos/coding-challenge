package org.example.api;

import org.example.ApiClient;

public class ApiCommon {
    public final static String BASE_URL = "https://gorest.co.in/public/v2";
    protected ApiClient client;

    public void setClient(ApiClient apiClient) {
        client = apiClient;
    }

    protected String getAccessTokenHeader() {
        return "Bearer " + System.getenv("ACCESS_TOKEN");
    }
}
