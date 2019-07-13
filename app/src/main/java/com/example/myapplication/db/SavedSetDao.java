package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SavedSetDao {

    @Query("SELECT * FROM saved_sets")
    public List<SavedSet> getAllSet();

}
