package com.example.myapplication.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "saved_sets")
public class SavedSet {

    @PrimaryKey
    public long _id;

    @ColumnInfo(name = "set_name")
    public String setName;

    public String getSetName() {
        return setName;
    }
}
