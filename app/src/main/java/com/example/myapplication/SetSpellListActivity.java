package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_spell_list);
        Bundle arguments = getIntent().getExtras();
        String test =  arguments.get("Set").toString();
//        setUserId = arguments.getInt("Set");
        setUserId = (long) arguments.get("Set");



        Log.i("Send from set", test);
        textTest = findViewById(R.id.textView3);
//        textTest.setText(setUserId);

        new AsyncTask<Void, Void, List<Spell>>() {
            @Override
            protected List<Spell> doInBackground(Void... voids) {
                AppDatabase db = App.getInstance().getDatabase();
                SpellDao spellDao = db.spellDao();
                final List<Spell> spells = AppDatabase.getInstance(SetSpellListActivity.this).spellDao().getAllSavedCurrentSetSpells(setUserId);
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
                            view = LayoutInflater.from(SetSpellListActivity.this).inflate(R.layout.spell_from_set, viewGroup, false);

                        TextView level = view.findViewById(R.id.spellLevel);
                        Integer levelSrt = spells2.get(position).level;
                        level.setText(levelSrt.toString());
                        TextView name = view.findViewById(R.id.spellName);
                        name.setText(spells2.get(position).name);

                        return view;
                    }


                };
                listView = findViewById(R.id.listViewSetSpell);
                listView.setAdapter(adapter);

//                listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
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


            } }.execute();
//TODO Контестное меню с возможностью удаления выбранного набора из лист вью

//        public boolean onCreateOptionsMenu (Menu menu)
//        @Override
//        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
//            super.onCreateContextMenu(menu, v, menuInfo);
//
//            MenuInflater inflater = getMenuInflater();
//            inflater.inflate(R.menu.context_menu, menu);
//        }
//        @Override
//        public boolean onContextItemSelected(MenuItem item) {
//            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//            switch (item.getItemId()) {
//                case R.id.edit:
//                    editItem(info.position); // метод, выполняющий действие при редактировании пункта меню
//                    return true;
//                case R.id.delete:
//                    deleteItem(info.position); //метод, выполняющий действие при удалении пункта меню
//                    return true;
//                default:
//                    return super.onContextItemSelected(item);

            }


    }


