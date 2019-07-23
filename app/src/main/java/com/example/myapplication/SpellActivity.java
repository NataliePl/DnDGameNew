package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.Spell;

import java.util.ArrayList;
import java.util.List;

import static java.security.CryptoPrimitive.MAC;


public class SpellActivity extends AppCompatActivity {
    String selectedClass;
    int selectedLevel;
    ListView listView;
    private List<Spell> selectedSpells = new ArrayList<>();
    AlertDialog dialog;
    AlertDialog.Builder alert;


    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell);
        Bundle arguments = getIntent().getExtras();
        selectedClass = arguments.get("Class").toString();
        selectedLevel = arguments.getInt("Level");
        final TextView textView = (TextView) findViewById(R.id.header);
        final TextView textView1 = (TextView) findViewById(R.id.textView2);

        new AsyncTask<Void, Void, List<Spell>>() {
            @Override
            protected List<Spell> doInBackground(Void... voids) {

                List<Spell> spells = getAllSpellsByLevelAndClass(selectedClass, selectedLevel);
                return spells;

            }

            @Override
            protected void onPostExecute(final List<Spell> spells) {

                fillListBySpellsByLevelAndClass(spells);
            }
        }.execute();


        final Button getChoice = (Button) findViewById(R.id.SpellSelected);
        getChoice.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        textView1.setText("Selected items are: " + selectedSpells);
                        SpellActivity.this.DialodWindow();
                    }
                }
        );
    }

    private void fillListBySpellsByLevelAndClass(final List<Spell> spells) {

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

    private List<Spell> getAllSpellsByLevelAndClass(String selectedClass, int selectedLevel) {

        final List<Spell> spells = AppDatabase.getInstance(SpellActivity.this).spellDao().getAllSelectedSpells(selectedClass, selectedLevel);
        return spells;
    }
//метод реализации диалогового окна
    void DialodWindow() {
        LayoutInflater li = LayoutInflater.from(SpellActivity.this);
        View promptsView = li.inflate(R.layout.dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SpellActivity.this);
        builder.setView(promptsView);
        builder.setTitle("Сохранение набора");

        final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);

        builder.setCancelable(false);
        builder.setNegativeButton("Отмена",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.setPositiveButton("Сохранить",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //todo код для сохранения выбранных заклинаний в БД в качестве отдельного набора
                        Toast.makeText(SpellActivity.this, "Вы сделали правильный выбор",
                                Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();


    }


}




