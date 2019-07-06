package com.example.myapplication.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "classes")
public class Class {

    @PrimaryKey
    public long _id;

    @ColumnInfo(name = "class_name")
    public String className;

}
