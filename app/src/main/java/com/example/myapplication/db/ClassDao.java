package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ClassDao {

    @Query("SELECT class_name FROM Class")
    public List<Class> getAllNameClasses();
}
