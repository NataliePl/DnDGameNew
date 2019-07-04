package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ClassDao {

    @Query("SELECT class_name FROM classes")
    public List<Class> getAllNameClasses();

    @Query("SELECT class_name FROM classes")
    public ClassName[] getAllNameClassesArr();
}
