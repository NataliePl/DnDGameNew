package com.example.myapplication.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "classes")
public class Class {

    @PrimaryKey
    public long _id;
    public String class_name;

}
