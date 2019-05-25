package com.atproj.movielib.app.modules;

import android.app.Application;
import android.content.Context;

import com.atproj.movielib.domain.repository.AuthenticationInterceptor;
import com.atproj.movielib.domain.repository.MovieLibService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.riversun.okhttp3.OkHttp3CookieHelper;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private String baseUrl;
    private String apiKey;

    public NetworkModule(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    @Provides CookieJar provideCookie() {
        OkHttp3CookieHelper cookieHelper = new OkHttp3CookieHelper();
        cookieHelper.setCookie(baseUrl, "cookie_name", "cookie_value");
        return cookieHelper.cookieJar();
    }

    @Provides
    Cache provideHttpCache(Application application) {
        int cachSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cachSize);
        return cache;
    }

    @Provides
    Interceptor provideInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    OkHttpClient provideOkHttpClient(Context context, CookieJar cookie, Cache cache, Interceptor interceptor) {
        OkHttpClient.Builder client = new OkHttpClient.Builder().cookieJar(cookie);
        client.cache(cache);
        client.addInterceptor(interceptor);
        client.addNetworkInterceptor(new AuthenticationInterceptor(apiKey));
        client.followRedirects(false);
        return client.build();
    }

    @Provides
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    Retrofit retrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();

    }

    @Provides
    MovieLibService movieLibService(Retrofit retrofit) {
        return retrofit.create(MovieLibService.class);
    }
}
