package com.atproj.movielib.domain.repository;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticationInterceptor implements Interceptor {

    private String apiKey;

    public AuthenticationInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request origRequest = chain.request();
        HttpUrl origHttpUrl = origRequest.url();

        HttpUrl authHttpUrl = origHttpUrl.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build();

        Request authRequest = origRequest.newBuilder()
                .url(authHttpUrl)
                .build();

        return chain.proceed(authRequest);
    }
}
