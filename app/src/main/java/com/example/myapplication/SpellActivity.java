package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.db.App;
import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.Spell;
import com.example.myapplication.db.SpellDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SpellActivity extends AppCompatActivity {
    String Class;
    int Level;
    ListView listView;
    View view;


    private List<Spell> selectedSpells = new ArrayList<>();

    String selected = "";
    TextView textView1;
//    Intent intent2 = new Intent(SpellActivity.this,SelectedSpellActivity.class);
    //вывод списка заклинаний

    //   private static final List<spells> spells = new Arraw

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell);
        Bundle arguments = getIntent().getExtras();
        Class = arguments.get("Class").toString();
        //Log.i("RFRF", Class);
        Level = arguments.getInt("Level");
        final TextView textView = (TextView) findViewById(R.id.header);
        final TextView textView1 = (TextView) findViewById(R.id.textView2);

        //            List<SpellLevelName> spells = spellDao.getAllShortSpells();
        new AsyncTask<Void, Void, List<Spell>>() {
            @Override
            protected List<Spell> doInBackground(Void... voids) {
                AppDatabase db = App.getInstance().getDatabase();
                SpellDao spellDao = db.spellDao();
                final List<Spell> spells = AppDatabase.getInstance(SpellActivity.this).spellDao().getAllSelectedSpells(Class, Level);
                return spells;

            }

            @Override
            protected void onPostExecute(final List<Spell> spells) {
                selectedSpells = new ArrayList<>();

                final BaseAdapter adapter = new BaseAdapter() {
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

                    public View getView(int position, View view, ViewGroup viewGroup) {
                        if (view == null)
                            view = LayoutInflater.from(SpellActivity.this).inflate(R.layout.spell, viewGroup, false);

                        final Spell spell = spells.get(position);

                        TextView level = view.findViewById(R.id.spellLevel);
                        CheckBox checkBox = view.findViewById(R.id.checkBox);

                        checkBox.setChecked(selectedSpells.contains(spell));
                        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                                if (checked)
                                    selectedSpells.add(spell);
                                else
                                    selectedSpells.remove(spell);
                            }
                        });

                        int levelSrt = spell.level; // todo getter?
                        level.setText(String.valueOf(levelSrt)); // Don't use Integer. You can use String.valueOf(levelSrt)
                        TextView name = view.findViewById(R.id.spellName);
                        name.setText(spell.name);

                        return view;
                    }


                };
                listView = findViewById(R.id.listShortSpell);
                listView.setAdapter(adapter);
//                listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

                listView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                String val = listView.getItemAtPosition(i).toString();
                                Intent intent = new Intent(SpellActivity.this, OneSpellActivity.class);
                                intent.putExtra("Spell", val);

                                startActivity(intent);

                            }

                        }

                );


            }
        }.execute();


        final Button getChoice = (Button) findViewById(R.id.SpellSelected);
        getChoice.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        textView1.setText("Selected items are: " + selectedSpells);
                    }
                }
        );
    }
}




