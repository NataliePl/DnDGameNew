package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface SpellDao {
    @Query("SELECT * FROM spells")
    List<Spell> getAllSpells();


    @Query("SELECT spell_name,spell_level,spell_nature,spell_during_time,spell_distance,spell_material,spell_read_time,spell_desc,spells._id FROM spells JOIN relations ON spells._id=relations.spell_relation_id  JOIN classes ON relations.class_relation_id=classes._id   WHERE classes.class_name=:sp and relations.class_level <= :lv")
    List<Spell> getAllSelectedSpells(String sp,int lv);

    @Query("SELECT spell_level, spell_name FROM spells")
    List<SpellLevelName> getAllShortSpells();



    @Insert
    void insertAll(Spell... spells);
}
