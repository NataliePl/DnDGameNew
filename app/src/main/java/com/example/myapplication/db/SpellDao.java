package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface SpellDao {
    @Query("SELECT * FROM spells")
    List<Spell> getAllSpells();


    @Query("SELECT spell_level, spell_name FROM spells")
    List<SpellLevelName> getAllShortSpells();



    @Insert
    void insertAll(Spell... spells);
}
