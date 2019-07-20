package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.db.App;
import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.SavedSet;
import com.example.myapplication.db.SavedSetDao;
import com.example.myapplication.db.Spell;
import com.example.myapplication.db.SpellDao;

import java.util.List;

public class SetsListActivity extends AppCompatActivity {
    ListView listView;
    AppDatabase db;
    Intent intent;
    long idSetForDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets_list);
        intent = new Intent(SetsListActivity.this, SetSpellListActivity.class);

        new AsyncTask<Void, Void, List<SavedSet>>() {
            @Override
            protected List<SavedSet> doInBackground(Void... voids) {
                List<SavedSet> savedSets = getAllUserSets();
                return savedSets;

            }

            @Override
            protected void onPostExecute(List<SavedSet> savedSets) {
                final List<SavedSet> savedSetsList = savedSets;
                setListSets(savedSetsList);

            }
        }.execute();

    }
//вынесение наборов в листвью. Адаптер
    private void setListSets(final List<SavedSet> savedSetsList) {

        final BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return savedSetsList.size();
            }

            @Override
            public Object getItem(int i) {
                return savedSetsList.get(i);
            }

            @Override
            public long getItemId(int i) {
                return savedSetsList.get(i)._id;
            }

            public View getView(int position, View view, ViewGroup viewGroup) {
                if (view == null)
                    view = LayoutInflater.from(SetsListActivity.this).inflate(R.layout.set_name, viewGroup, false);

                TextView name = view.findViewById(R.id.setName);
                String setNameText = savedSetsList.get(position).setName;
                name.setText(setNameText);

                return view;
            }


        };
        listView = findViewById(R.id.listViewSetsName);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        SavedSet tmp = (SavedSet) adapterView.getItemAtPosition(i);
                        Long id = tmp.get_id();
                        String idStr = id.toString();
                        Intent intent2 = new Intent(SetsListActivity.this, SetSpellListActivity.class);
                        intent2.putExtra("Set", id);
                        startActivity(intent2);

                    }


                }

        );
    }

    private List<SavedSet> getAllUserSets() {
        db = App.getInstance().getDatabase();
        SavedSetDao savedSetDao = db.savedSetDao();
        final List<SavedSet> savedSets = AppDatabase.getInstance(SetsListActivity.this).savedSetDao().getAllSets();
        return savedSets;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        return;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                return true;
            case R.id.delete:

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Integer id2 = info.position;

                SavedSet a = (SavedSet) listView.getItemAtPosition(id2);
                idSetForDel = a.get_id();

                new AsyncTask<Long, Void, List<SavedSet>>() {


                    @Override
                    protected List<SavedSet> doInBackground(Long... voids) {
                        db = App.getInstance().getDatabase();
                        SavedSetDao savedSetDao = db.savedSetDao();
                        AppDatabase.getInstance(SetsListActivity.this).savedSetDao().deleteSetById(idSetForDel);
                        final List<SavedSet> savedSets2 = AppDatabase.getInstance(SetsListActivity.this).savedSetDao().getAllSets();

                        return savedSets2;

                    }

                    @Override
                    protected void onPostExecute(List<SavedSet> savedSets2) {
                        final List<SavedSet> savedSetsList = savedSets2;
                        final BaseAdapter adapterNew = new BaseAdapter() {
                            @Override
                            public int getCount() {
                                return savedSetsList.size();
                            }

                            @Override
                            public Object getItem(int i) {
                                return savedSetsList.get(i);
                            }

                            @Override
                            public long getItemId(int i) {
                                return savedSetsList.get(i)._id;
                            }

                            public View getView(int position, View view, ViewGroup viewGroup) {
                                if (view == null)
                                    view = LayoutInflater.from(SetsListActivity.this).inflate(R.layout.set_name, viewGroup, false);

                                TextView name = view.findViewById(R.id.setName);
                                String setNameText = savedSetsList.get(position).setName;
                                name.setText(setNameText);

                                return view;
                            }


                        };
                        ListView listView = findViewById(R.id.listViewSetsName);
                        listView.setAdapter(adapterNew);
                    }


                };

                return true;
        }
        return super.onContextItemSelected(item);
    }


}
