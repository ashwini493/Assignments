package com.example.assignment.webservices;
/* Created By Ashwini Saraf on 4/06/2022*/

import com.example.assignment.model.DataResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("facts.json")
    Call<DataResponse> getAllData();

}
