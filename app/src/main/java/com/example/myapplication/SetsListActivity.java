package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets_list);
        intent = new Intent(SetsListActivity.this,SetSpellListActivity.class);

        new AsyncTask<Void, Void, List<SavedSet>>() {
            @Override
            protected List<SavedSet> doInBackground(Void... voids) {
                db = App.getInstance().getDatabase();
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
                registerForContextMenu(listView);
//                listView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                listView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                String val = listView.getItemAtPosition(i).toString();
                                SavedSet tmp=(SavedSet) adapterView.getItemAtPosition(i);
                                Long id = tmp.get_id();
                                String idStr=id.toString();
//                                String val = savedSetsList[position];
                                //TODO Intent получает ID. Но в SetSpellListActivity приходит, судя по всему, null. При этом отображаются заклинания после запроса словно выбран id1 при любом из нажатий
//                                SavedSet val = new SavedSet();
//                                String a = listView.getSelectedItem().toString();
//                                String a = listView.getItemAtPosition(i).toString();
//                                String val = listView.getAdapter().getItemId(a);
                                Intent intent2 = new Intent(SetsListActivity.this,SetSpellListActivity.class);
                                intent2.putExtra("Set", id);

                                startActivity(intent2);

                            }

                        }

                );


            } }.execute();


    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        return;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                return true;
            case R.id.delete:
//                long id = item.getItemId();
//                String a = Long.toString(id);
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Integer id2 = info.position;
//                SavedSet tmp=(SavedSet) adapterView.getItemAtPosition(id2);
//                Long id = tmp.get_id();
////                SavedSet tmp=(SavedSet) item.getItemAtPosition(i);
////                Long id = tmp.get_id();
////                item.
////                SavedSet tmp=(SavedSet)item.getItemId();
////                Long id = tmp.get_id();
                Toast toast = Toast.makeText(getApplicationContext(),
                "Ваш выбор: " + info, Toast.LENGTH_SHORT);
                toast.show();
////                db.savedSetDao().deleteSetById(2);
////                String b= "ла ла";
                return true;
        }
        return super.onContextItemSelected(item);
    }

    //        @Override
//        public boolean  onContextItemSelected(MenuItem item){

}
