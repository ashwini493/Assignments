package com.example.assignment.webservices;

import com.example.assignment.model.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("facts.json")
    Call<DataResponse> getAllData();
}
