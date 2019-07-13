package com.example.myapplication.db;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (tableName = "saved_set_spells",
        foreignKeys =
        @ForeignKey (entity = SavedSet.class,
        parentColumns = "_id",
        childColumns = "saved_set_id",
        onDelete = ForeignKey.CASCADE))

public class SavedSetSpell {

    @PrimaryKey
    public long _id;

    public int saved_set_id;
    public int spell_id;

}
