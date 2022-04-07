package com.example.assignment.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String SERVER_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS);
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder();
                requestBuilder.header("Accept", "application/json");
                requestBuilder.header("Content-Type", "application/json");
                requestBuilder.header("Content-Type", "application/x-www-form-urlencoded");
                requestBuilder.method(original.method(), original.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            });
            httpClient.addInterceptor(logging);

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();
        }

        return retrofit;
    }
}


