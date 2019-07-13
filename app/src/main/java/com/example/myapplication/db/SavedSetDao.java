package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SavedSetDao {

    @Query("SELECT * FROM saved_sets")
    public List<SavedSet> getAllSets();

    @Query("DELETE from saved_sets WHERE _id =:idSet")
    public void deleteSetById(long idSet);

}
