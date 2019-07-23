package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.db.App;
import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.Spell;
import com.example.myapplication.db.SpellDao;

import java.util.List;

public class SetSpellListActivity extends AppCompatActivity {
    long setUserId;
    TextView textTest;
    ListView listView;
    SpellAdapter spellAdapter;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_spell_list);
        Bundle arguments = getIntent().getExtras();
        String test = arguments.get("Set").toString();
        setUserId = (long) arguments.get("Set");
        Log.i("Send from set", test);
        textTest = findViewById(R.id.textView3);


        new AsyncTask<Void, Void, List<Spell>>() {
            @Override
            protected List<Spell> doInBackground(Void... voids) {
                List<Spell> spells = getAllSetsSpells(setUserId);
                return spells;

            }

            @Override
            protected void onPostExecute(List<Spell> spells) {
                final List<Spell> spells2 = spells;
                fillSetSpellList(spells2);


            }
        }.execute();

    }

    private void fillSetSpellList(final List<Spell> spells2) {

        spellAdapter = new SpellAdapter(this, spells2);
        listView = findViewById(R.id.listViewSetSpell);
        listView.setAdapter(spellAdapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String val = listView.getItemAtPosition(i).toString();
                        Intent intent = new Intent(SetSpellListActivity.this, OneSpellActivity.class);
                        intent.putExtra("Spell", val);

                        startActivity(intent);

                    }

                }

        );
    }

    private List<Spell> getAllSetsSpells(long setUserId) {

        final List<Spell> spells = AppDatabase.getInstance(SetSpellListActivity.this).spellDao().getAllSavedCurrentSetSpells(setUserId);
        return spells;
    }

}


