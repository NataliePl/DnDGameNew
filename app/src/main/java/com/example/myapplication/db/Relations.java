package com.example.myapplication.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
//        (foreignKeys = @ForeignKey(entity = Class.class, parentColumns = "_id", childColumns = "class_relation_id")

public class Relations {

    @PrimaryKey
    public long _id;

    public int class_relation_id;
    public int spell_relation_id;
    public int class_level;

}
