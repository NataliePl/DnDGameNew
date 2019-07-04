package com.example.myapplication.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Relations {

    @PrimaryKey
    public long _id;

    public int class_relation_id;
    public int spell_relation_id;
    public int class_level;

}
