package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.db.App;
import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.SavedSet;
import com.example.myapplication.db.SavedSetDao;
import com.example.myapplication.db.Spell;
import com.example.myapplication.db.SpellDao;

import java.util.List;

public class SetsListActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets_list);
        Intent intent = new Intent(SetsListActivity.this,SetSpellListActivity.class);

        new AsyncTask<Void, Void, List<SavedSet>>() {
            @Override
            protected List<SavedSet> doInBackground(Void... voids) {
                AppDatabase db = App.getInstance().getDatabase();
                SavedSetDao savedSetDao = db.savedSetDao();
                final List<SavedSet> savedSets = AppDatabase.getInstance(SetsListActivity.this).savedSetDao().getAllSets();
                return savedSets;

            }

            @Override
            protected void onPostExecute(List<SavedSet> savedSets) {
                final List<SavedSet> savedSetsList = savedSets;
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
//                listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                listView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                String val = listView.getItemAtPosition(i).toString();
                                SavedSet tmp=(SavedSet) adapterView.getItemAtPosition(i);
                                Long id = tmp.get_id();
//                                String val = savedSetsList[position];

//                                SavedSet val = new SavedSet();
//                                String a = listView.getSelectedItem().toString();
//                                String a = listView.getItemAtPosition(i).toString();
//                                String val = listView.getAdapter().getItemId(a);
                                Intent intent = new Intent(SetsListActivity.this,SetSpellListActivity.class);
                                intent.putExtra("Set", id);

                                startActivity(intent);

                            }

                        }

                );


            } }.execute();


    }
}
