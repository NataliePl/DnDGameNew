package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.App;
import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.Spell;
import com.example.myapplication.db.SpellDao;

import java.util.List;

import static java.lang.Long.parseLong;

public class OneSpellActivity extends AppCompatActivity {
String spell;
    //вывод списка заклинаний

    //   private static final List<spells> spells = new Arraw

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell);
        Bundle arguments = getIntent().getExtras();
        spell = arguments.get("Spell").toString();
        Log.i("RFRF", spell);
        final TextView textView = (TextView) findViewById(R.id.header);
        String[] subStrSpell;
        String delimeter = ","; // Разделитель
        subStrSpell = spell.split(delimeter); // Разделения строки str с помощью метода split()
        String delimeter2 = "="; // Разделитель
        String[] subStrForId;
        subStrForId = subStrSpell[0].split(delimeter2);
        final Long idSpell = parseLong(subStrForId[1]);




        //            List<SpellLevelName> spells = spellDao.getAllShortSpells();
        new AsyncTask<Void, Void, Spell>() {
        @Override
           protected Spell doInBackground(Void... voids) {
            AppDatabase db = App.getInstance().getDatabase();
            SpellDao spellDao = db.spellDao();
            Spell curSpell = new Spell();
            curSpell = AppDatabase.getInstance(OneSpellActivity.this).spellDao().getSpellById(idSpell);
            return curSpell;


        }
            @Override
            protected void onPostExecute(Spell curSpell) {

                TextView textViewSpellName = (TextView) findViewById(R.id.spellName);
                textViewSpellName.setText(curSpell.name);


//                final List<Spell> spells2 = spells;
//                BaseAdapter adapter = new BaseAdapter() {
//                    @Override
//                    public int getCount() {
//                        return spells2.size();
//                    }
//                @Override
//                public Object getItem ( int i){
//                    return spells2.get(i);
//                }
//
//                @Override
//                public long getItemId ( int i){
//                    return spells2.get(i)._id;
//                }
//
//                public View getView(int position, View view, ViewGroup viewGroup) {
//                    if(view == null)
//                        view = LayoutInflater.from(OneSpellActivity.this).inflate(R.layout.spell, viewGroup, false);
//
//
//
//                    TextView level = view.findViewById(R.id.spellLevel);
//                    Integer levelSrt = spells2.get(position).level;
//                    level.setText(levelSrt.toString());
//                    TextView name = view.findViewById(R.id.spellName);
//                    name.setText(spells2.get(position).name);
//
//                    return view;
//                 }


//            };
//                ListView listView = findViewById(R.id.listShortSpell);
//                listView.setAdapter(adapter);
        }}.execute();
    }



        {


        };
    }





//        new AsyncTask<Void, Void, List<Spell>>() {
//            @Override
//            protected List<Spell> doInBackground(Void... voids) {
//                List<Spell> spells = AppDatabase.getInstance(SpellActivity.this).spellDao().getAllSpells();
//                Log.d("SpellActivity", spells.toString());
//                return spells;
//            }
//        }.execute();



/*
protected List<Spell> doInBackground(Void... voids) {

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
*/


