package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.myapplication.db.App;
import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.Spell;
import com.example.myapplication.db.SpellDao;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SpellViewModel extends ViewModel {
    private List<Spell> spellsByLevelAndClass;


private MutableLiveData<List<Spell>> allSetSpells;

    public MutableLiveData<List<Spell>> getAllSetSpellsById (final long setUserId) {
        if (allSetSpells == null) {
            allSetSpells = new MutableLiveData<>();
            loadAllsetSpellsById(setUserId);

            }
        return  allSetSpells;
        }


    private void loadAllsetSpellsById (long setUserId) {

        AppDatabase db = App.getInstance().getDatabase();
        SpellDao spellDao = db.spellDao();
        List<Spell> allSetSpells = spellDao.getAllSavedCurrentSetSpells(setUserId);

    }

    public List<Spell> getAllSetSpellsByLevelAndClass (String cl, int lev) {
        if (spellsByLevelAndClass == null) {
            spellsByLevelAndClass = new List<Spell>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(@Nullable Object o) {
                    return false;
                }

                @NonNull
                @Override
                public Iterator<Spell> iterator() {
                    return null;
                }

                @NonNull
                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @NonNull
                @Override
                public <T> T[] toArray(@NonNull T[] ts) {
                    return null;
                }

                @Override
                public boolean add(Spell spell) {
                    return false;
                }

                @Override
                public boolean remove(@Nullable Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(@NonNull Collection<?> collection) {
                    return false;
                }

                @Override
                public boolean addAll(@NonNull Collection<? extends Spell> collection) {
                    return false;
                }

                @Override
                public boolean addAll(int i, Collection<? extends Spell> collection) {
                    return false;
                }

                @Override
                public boolean removeAll(@NonNull Collection<?> collection) {
                    return false;
                }

                @Override
                public boolean retainAll(@NonNull Collection<?> collection) {
                    return false;
                }

                @Override
                public void clear() {

                }

                @Override
                public boolean equals(@Nullable Object o) {
                    return false;
                }

                @Override
                public int hashCode() {
                    return 0;
                }

                @Override
                public Spell get(int i) {
                    return null;
                }

                @Override
                public Spell set(int i, Spell spell) {
                    return null;
                }

                @Override
                public void add(int i, Spell spell) {

                }

                @Override
                public Spell remove(int i) {
                    return null;
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }

                @Override
                public int lastIndexOf(Object o) {
                    return 0;
                }

                @Override
                public ListIterator<Spell> listIterator() {
                    return null;
                }

                @Override
                public ListIterator<Spell> listIterator(int i) {
                    return null;
                }

                @Override
                public List<Spell> subList(int i, int i1) {
                    return null;
                }
            };
            loadSpellsByLevelAndClass(cl, lev);

        }
        return  spellsByLevelAndClass;
    }

    private void loadSpellsByLevelAndClass (String cl, int lev) {
        AppDatabase db = App.getInstance().getDatabase();
        SpellDao spellDao = db.spellDao();
        List<Spell> spellsByLevelAndClass = spellDao.getAllSelectedSpells(cl, lev);
    }

}
