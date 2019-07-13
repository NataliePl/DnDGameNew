package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface SpellDao {
    @Query("SELECT * FROM spells")
    List<Spell> getAllSpells();


    @Query("SELECT spell_name,spell_level,spell_nature,spell_during_time,spell_distance,spell_material,spell_read_time,spell_desc,spells._id " +
            "FROM spells " +
            "JOIN relations ON spells._id=relations.spell_relation_id  " +
            "JOIN classes ON relations.class_relation_id=classes._id   " +
            "WHERE classes.class_name=:sp and relations.class_level <= :lv")
    List<Spell> getAllSelectedSpells(String sp,int lv);

    @Query("SELECT spell_level, spell_name FROM spells")
    List<SpellLevelName> getAllShortSpells();

    @Query("SELECT * FROM spells WHERE _id = :id")
    Spell getSpellById (long id);

    @Query("SELECT spell_name,spell_level,spell_nature,spell_during_time,spell_distance,spell_material,spell_read_time,spell_desc,spells._id " +
            "FROM spells " +
            "JOIN saved_sets ON saved_sets._id=:idSet " +
            "JOIN saved_set_spells ON saved_set_spells.spell_id=spells._id   " +
            "WHERE saved_sets._id=:idSet ")
    List<Spell> getAllSavedCurrentSetSpells(long idSet);

    @Insert
    void insertAll(Spell... spells);
}
