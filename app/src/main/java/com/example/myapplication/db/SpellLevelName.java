package com.example.myapplication.db;

import androidx.room.ColumnInfo;

public class SpellLevelName {

    @ColumnInfo(name = "spell_level")
    public int spell_level;

    @ColumnInfo(name = "spell_name")
    public String spell_name;

}
