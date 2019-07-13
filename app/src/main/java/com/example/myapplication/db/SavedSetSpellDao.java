package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SavedSetSpellDao {
    @Query("SELECT * FROM saved_set_spells")
    public List<SavedSetSpell> getAllSetSpells();

}
