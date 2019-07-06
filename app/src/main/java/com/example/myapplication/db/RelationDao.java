package com.example.myapplication.db;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

@Dao
public interface RelationDao {

    @Query("SELECT _id, class_relation_id, spell_relation_id, class_level FROM relations")
    public List<Relations> getAllRelations();


}
