package com.example.myapplication.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "spells")
public class Spell {


    @PrimaryKey
    public long _id;

    @ColumnInfo(name = "spell_name")
    public String name;

    @ColumnInfo(name = "spell_level")
    public int level;

    public String spell_nature;
    public String spell_during_time;
    public String spell_distance;
    public String spell_material;
    public String spell_read_time;
    public String spell_desc;


    @Override
    public String toString() {
        return "Spell{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", spell_nature='" + spell_nature + '\'' +
                ", spell_during_time='" + spell_during_time + '\'' +
                ", spell_distance='" + spell_distance + '\'' +
                ", spell_material='" + spell_material + '\'' +
                ", spell_read_time='" + spell_read_time + '\'' +
                ", spell_desc='" + spell_desc + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
}
