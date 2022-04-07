package com.example.assignment.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.assignment.model.Data;

import java.util.List;

public class DataRepository {


    private final Dao dao;
    private final LiveData<List<Data>> allData;

    public DataRepository(Application application) {
        DataDatabase database = DataDatabase.getInstance(application);
        dao = database.Dao();
        allData = dao.getAllData();
    }

    public void insert(List<Data> model) {
        new InsertDataAsyncTask(dao).execute(model);
    }

    public void update(List<Data> list) {
        new UpdateDataAsyncTask(dao).execute(list);
    }

    public LiveData<List<Data>> getAllData() {
        return allData;
    }

    public static class InsertDataAsyncTask extends AsyncTask<List<Data>, Void, Void> {
        private final Dao dao;

        private InsertDataAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(List<Data>... model) {
            // below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    private static class UpdateDataAsyncTask extends AsyncTask<List<Data>, Void, Void> {
        private final Dao dao;

        private UpdateDataAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(List<Data>... models) {
            // below line is use to update
            // our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

}
