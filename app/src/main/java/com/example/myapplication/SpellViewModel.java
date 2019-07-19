package com.example.myapplication;


import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.myapplication.db.App;
import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.Spell;
import com.example.myapplication.db.SpellDao;
import com.example.myapplication.db.SpellRepository;

import java.util.List;

public class SpellViewModel extends AndroidViewModel {

    private SpellRepository repository;
    private List<Spell> allSpells;
    private List<Spell> allSetSpells;
    private List<Spell> spells2;
    MyTask mt;
    TextView tvInfo;


    public SpellViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = App.getInstance().getDatabase();
                SpellDao spellDao = db.spellDao();


//        allSpells = spellDao.getAllSpells();
//                final List<Spell> spells = AppDatabase.getInstance(a.spellDao().getAllSavedCurrentSetSpells(setUserId);


    }



//    public List<Spell> getAllSpells() {
//        return allSpells;
//    }

    public List<Spell> getAllSetSpells(final long setUserId) {
//        final List<Spell>[] allSetSpells = new List<Spell>[1];
        new AsyncTask<Long, Void, List<Spell>>() {


            @Override
            protected List<Spell> doInBackground(Long... values) {
//                public List<Spell> allSetSpells;
                AppDatabase db = App.getInstance().getDatabase();
                SpellDao spellDao = db.spellDao();
                allSetSpells = spellDao.getAllSavedCurrentSetSpells(setUserId);
                String a = allSetSpells.toString();
//                final List<Spell> spells = AppDatabase.getInstance().getDatabase().spellDao().getAllSavedCurrentSetSpells(setUserId);

//                allSetSpells[0] = (List<Spell>) repository.getAllSpells();
//                final List<Spell> spells = AppDatabase.getInstance(a.spellDao().getAllSavedCurrentSetSpells(setUserId);
                return allSetSpells;

            }
            @Override
            protected void onPostExecute(List<Spell> allSetSpells) {
                spells2 = allSetSpells;}
//

        }.execute();
return spells2;
//        return spells2;
    }



//    public void getSpellsByClassAndLevel (String className, int level){
//
//        new AsyncTask<Void, Void, List<Spell>>() {
//            @Override
//            protected List<Spell> doInBackground(Void... voids) {
//                AppDatabase db = App.getInstance().getDatabase();
//                SpellDao spellDao = db.spellDao();
//                final List<Spell> spells = AppDatabase.getInstance(SpellActivity.this).spellDao().getAllSelectedSpells(className, level);
//                return spells;
//
//            }
//
//    }
//}


}
