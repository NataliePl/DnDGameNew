package com.example.myapplication.db;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity  (tableName = "saved_set_spells")
public class SavedSetSpell {

    @PrimaryKey
    public long _id;

    public int saved_set_id;
    public int spell_id;

}
