package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.db.App;
import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.Spell;
import com.example.myapplication.db.SpellDao;
import com.example.myapplication.db.SpellLevelName;

import java.util.List;

public class SpellActivity extends AppCompatActivity {

    //вывод списка заклинаний
    AppDatabase db = App.getInstance().getDatabase();
    SpellDao spellDao = db.spellDao();
    //   private static final List<spells> spells = new Arraw

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell);

        ListView listView = findViewById(R.id.listShortSpell);
        final TextView textView = (TextView) findViewById(R.id.header);
/**
 new AsyncTask<Void, Void, List<SpellLevelName>>() {

 // Обязательно НЕ в UI потоке, поэтому doInBackground
 @Override protected List<SpellLevelName> doInBackground(Void... voids) {
 return AppDatabase.getInstance(SpellActivity.this).spellDao().getAllShortSpells();
 }


 // Обязательно в UI потоке, поэтому onPostExecute
 @Override protected void onPostExecute(List<SpellLevelName> spells) {
 ArrayAdapter<SpellLevelName> adapter=new ArrayAdapter<SpellLevelName>(this,android.R.layout.spell, spells);
 //       listShortSpell.setAdapter(adapter);
 textView.setText(spells.toString());
 }
 }.execute();
 */
        //            List<SpellLevelName> spells = spellDao.getAllShortSpells();
        final List<Spell> spells = AppDatabase.getInstance(SpellActivity.this).spellDao().getAllSpells();
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return spells.size();
            }

            @Override
            public Object getItem(int i) {
                return spells.get(i);
            }

            @Override
            public long getItemId(int i) {
                return spells.get(i)._id;
            }

            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                if(view == null)
                    view = LayoutInflater.from(SpellActivity.this).inflate(R.layout.spell, viewGroup, false);

                TextView level = view.findViewById(R.id.spellLevel);
                level.setText(spells.get(position).level);

                return view;
            }
        };

        listView.setAdapter(adapter);


//        new AsyncTask<Void, Void, List<Spell>>() {
//            @Override
//            protected List<Spell> doInBackground(Void... voids) {
//                List<Spell> spells = AppDatabase.getInstance(SpellActivity.this).spellDao().getAllSpells();
//                Log.d("SpellActivity", spells.toString());
//                return spells;
//            }
//        }.execute();


    }


}
