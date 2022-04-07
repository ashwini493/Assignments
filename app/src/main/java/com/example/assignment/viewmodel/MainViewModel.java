package com.example.assignment.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.assignment.R;
import com.example.assignment.model.Data;
import com.example.assignment.model.DataResponse;
import com.example.assignment.roomdatabase.DataRepository;
import com.example.assignment.utility.UtilsMethods;
import com.example.assignment.webservices.ApiClient;
import com.example.assignment.webservices.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    MutableLiveData<List<Data>> liveData;
    private List<Data> dataList;
    Context context;
    private ApiInterface apiInterface;
    private DataRepository repository;
    public String title;

    public MainViewModel(Application context) {
        super(context);
        this.context = context.getApplicationContext();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        liveData = new MutableLiveData<>();
        repository = new DataRepository(context);
        init();
    }

    public MutableLiveData<List<Data>> getMutableLiveData() {
        return liveData;
    }


    public void init() {

        if (UtilsMethods.isNetworkAvailable(context))
            getAllData();
        else
            UtilsMethods.showText(context, context.getString(R.string.no_network_available));
    }


    private void insert() {
        repository.insert(dataList);
    }

    private void getAllData() {

        apiInterface.getAllData().enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(@NonNull Call<DataResponse> call, @NonNull Response<DataResponse> response) {

                if (response.body() != null) {
                    title = response.body().getTitle();
                    dataList = response.body().getRows();
                    liveData.setValue(dataList);
                    insert();
                } else
                    UtilsMethods.showText(context, context.getString(R.string.no_response_found));

            }

            @Override
            public void onFailure(@NonNull Call<DataResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
