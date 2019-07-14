package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.db.App;
import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.Class;
import com.example.myapplication.db.Spell;
import com.example.myapplication.db.SpellDao;
import com.example.myapplication.db.SpellLevelName;

import java.util.List;


public class SpellActivity extends AppCompatActivity {
    String Class;
    int Level;
    ListView listView;
    View view;

    String selected="";
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
            protected void onPostExecute(List<Spell> spells) {
                final List<Spell> spells2 = spells;
                final BaseAdapter adapter = new BaseAdapter() {
                    @Override
                    public int getCount() {
                        return spells2.size();
                    }

                    @Override
                    public Object getItem(int i) {
                        return spells2.get(i);
                    }

                    @Override
                    public long getItemId(int i) {
                        return spells2.get(i)._id;
                    }

                    public View getView(int position, View view, ViewGroup viewGroup) {
                        if (view == null)
                            view = LayoutInflater.from(SpellActivity.this).inflate(R.layout.spell, viewGroup, false);

                        TextView level = view.findViewById(R.id.spellLevel);
                        Integer levelSrt = spells2.get(position).level;
                        level.setText(levelSrt.toString());
                        TextView name = view.findViewById(R.id.spellName);
                        name.setText(spells2.get(position).name);

                        return view;
                    }


                };
                listView = findViewById(R.id.listShortSpell);
                listView.setAdapter(adapter);
                listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

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


            } }.execute();

        //TODO возможность по клику кнопки сохранить множественный выбор из лист вью. Со всплывающим окном для внесения названия набора
        final CheckBox checkBoxSpell = (CheckBox) findViewById(R.id.checkBox);
//        checkBoxSpell.isChecked();


//        checkBoxSpell.setOnClickListener(new View.OnClickListener() {
//        });

        //немного работающий код, но считывает не чек бокс а айтем с листвью, да еще и возникает проблема с корректностью выборки.
                final Button getChoice = (Button) findViewById(R.id.SpellSelected);
       getChoice.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

//                        SparseBooleanArray sp = listView.getCheckedItemPositions();

                        CheckBox spell = (CheckBox) findViewById(R.id.checkBox);

                        String selectedItems = "";
                        int sp = listView.getAdapter().getCount();
//                        SparseBooleanArray sp = listView.getCheckedItemPositions();
                        StringBuilder sb = new StringBuilder();
                        // Получаем, отмечен ли данный флажок
                        ListView selection = (ListView) findViewById(R.id.listShortSpell);
                        for (int i = 0; i < sp; i++) {


                            if (spell.isChecked()) {
                                Integer test=spell.getId();
//                                Class test2=spell.getClass();
//                                Spell SelectedSpell = new Spell;

                                String test2=test.toString();

                                selectedItems += test2;
                            } else
                                continue;

//
//                        StringBuilder sb = new StringBuilder();
//
//                        for (int i = 0; i < sp.size(); i++) {
//                            if (sp.valueAt(i) == true) {
//                                Spell spellItem = (Spell) listView.getItemAtPosition(i);
//                                // Or:
//                                // String s = ((CheckedTextView) listView.getChildAt(i)).getText().toString();
//                                String s = spellItem.getName();
//                                sb = sb.append(" " + s);
//                            }
//                        }

                        }
                        textView1.setText("Selected items are: " + selectedItems.toString());
                    }
                }
       );
                }
//    public void onCheckBoxClicked(View view){
//
//        // Получаем флажок
//        CheckBox spell = (CheckBox) view;
//        String selectedItems = "";
//        SparseBooleanArray sp = listView.getCheckedItemPositions();
//        StringBuilder sb = new StringBuilder();
//        // Получаем, отмечен ли данный флажок
//        ListView selection = (ListView) findViewById(R.id.listShortSpell);
//        for (int i = 0; i < sp.size(); i++) {
//
//        if(spell.isChecked()){
//            selectedItems += spell.getText();
//           }
//        else
//            continue;}


    }







//        Button getChoice = (Button) findViewById(R.id.SpellSelected);
//        getChoice.setOnClickListener(
//                new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View view) {
//
//
//                        int cntChoice = listView.getCount();
//
//                        SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
//
//                        for (int i = 0; i < cntChoice; i++) {
//
//                            if (sparseBooleanArray.get(i)) {
//
//                                selected += listView.getItemAtPosition(i).toString() + "\n";
//                            }
//
//                        }
//
//                        textView1 = (TextView) findViewById(R.id.textView2);
//                        textView1.setText(selected);
//                        startActivity(intent2);
//                    }}
//                    );

            //                Toast toast = Toast.makeText(getApplicationContext(),
//                        "Ваш выбор: " + selected, Toast.LENGTH_SHORT);
//                toast.show();


//    @Override
//    public void onClick(View view) {
//        SparseBooleanArray bb = listView.getCheckedItemPositions();
//        Log.d("MyLog", "click");
//        for(int i = 0; i < bb.size(); i++){
//            Log.d("MyLog", "ok");
//            int key = bb.keyAt(i);
//            if(bb.get(key)){
//
//            }
//            String val =listView.getItemAtPosition(i).toString();
//            Log.d("MyLog", val);




