package com.example.myapplication.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SpellRepository {

    private SpellDao spellDao;
    private SavedSet savedSet;
    private SavedSetSpellDao savedSetSpellDao;
    private List<Spell> allSpells;
    private List<Spell> allSetSpells;
    private String selectedClassName;
    private int selectedLevel;
    public long id;

    public SpellRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        spellDao = database.spellDao();
        allSpells = spellDao.getAllSpells();
//        allSetSpells = spellDao.getAllSavedCurrentSetSpells(id);

    }

    public List<Spell> getAllSpells() {
        return allSpells;
    }

//    public List<Spell> getAllSetSpells(SavedSet savedSet){
//        new getAllSetSpellsAsyncTask(spellDao).execute(savedSet);
//    }
//
//    private static class getAllSetSpellsAsyncTask extends AsyncTask<SavedSet, Void, Void> {
//        private SpellDao spellAsyncTaskDao;
//
//
//    }

//    public List<Spell> getAllSpells(){
//        return allSpells;
//    }
//
//    public List<Spell> getAllSetSpellsById(long id){
//        new  getAllSetSpells(spellDao).execute(long id);
//    };
//
//
//    private static class getAllSetSpells extends AsyncTask<long id, void, void> {
//        private SpellDao spellDao;
//
//        private getAllSetSpells(SpellDao spellDao){
//
//            this.spellDao = spellDao;
//
//        }
//
//
//        @Override
//        protected List<Spell> doInBackground(Spell... spell) {
//            List<Spell> allSetSpells;
//            allSetSpells = savedSetSpellDao.getAllSetSpells();
//            return List<Spell>;
//
//        }
//    }

}
