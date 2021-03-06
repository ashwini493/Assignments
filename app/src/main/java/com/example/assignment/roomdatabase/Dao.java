package com.example.assignment.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment.model.Data;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    void insert(List<Data> model);

    @Query("SELECT * FROM data_table")
    LiveData<List<Data>> getAllData();
}
