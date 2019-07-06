package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.Spell;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Спиннеры
    String[] classes = {"Волшебник", "Чародей", "Бард", "Паладин"};
    String[] levels = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    //Работа с БД
    Button button;
    TextView textView;

    Intent intent;

    private SQLiteDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(MainActivity.this,SpellActivity.class);
//       final MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        //спиннеры
//        Spinner spinnerClasses = (Spinner) findViewById(R.id.spin_classes);
//
//        ArrayAdapter<String> adapterClasses = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classes);
//        adapterClasses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerClasses.setAdapter(adapterClasses);
//// https://stackoverflow.com/questions/1625249/android-how-to-bind-spinner-to-custom-object-list отправка имен классов в спиннер
//        new AsyncTask<Void, Void, List<Class>>() {
//            @Override
//            protected List<Class> doInBackground(Void... voids) {
//                AppDatabase db = App.getInstance().getDatabase();
//                SpellDao spellDao = db.spellDao();
//
//                final List<Class> classes = AppDatabase.getInstance(MainActivity.this).classDao().getAllClasses();
//                return classes;
//
//            }
//            @Override
//            protected void onPostExecute(List<Class> classes) {
//                final List<Class> classesAll = classes;
//                Spinner spinnerClasses = (Spinner) findViewById(R.id.spin_classes);
//                ArrayAdapter <> adapter = ArrayAdapter.createFromResource(this, classesAll, android.R.layout.simple_spinner_item); )
//
//
//
////                BaseAdapter adapter = new BaseAdapter() {
////                    @Override
////                    public int getCount() {
////                        return classesAll.size();
////                    }
////                    @Override
////                    public Object getItem ( int i){
////                        return classesAll.get(i);
////                    }
////
////                    @Override
////                    public long getItemId ( int i){
////                        return classesAll.get(i)._id;
////                    }
////                    public View getView(int position, View view, ViewGroup viewGroup) {
////                        if(view == null)
////                            view = LayoutInflater.from(MainActivity.this).inflate(R.layout.simple_spinner_dropdown_item, viewGroup, false);
////
////                        TextView nameClass = view.findViewById(R.id.simple_spinner_dropdown_item);
////                        nameClass.setText(classesAll.get(position).className);
////
////                        return view;
//                    }
//
//
//        };
//
//        spinnerClasses.setAdapter(adapter);
//        }}.execute();

        final Spinner spinnerClasses = (Spinner) findViewById(R.id.spin_classes);
        ArrayAdapter<String> adapterClasses = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classes);
        adapterClasses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClasses.setAdapter(adapterClasses);
        spinnerClasses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                //String[] choose = getResources().getStringArray(R.array.animals);
                String selected = spinnerClasses.getSelectedItem().toString();
                intent.putExtra("Class", selected);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ваш выбор: " + selected, Toast.LENGTH_SHORT);
                toast.show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        final Spinner spinnerLevels = (Spinner) findViewById(R.id.spin_levels);
        ArrayAdapter<String> adapterLevels = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, levels);
        adapterLevels.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevels.setAdapter(adapterLevels);
        spinnerLevels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                //String[] choose = getResources().getStringArray(R.array.animals);
                String selected = spinnerLevels.getSelectedItem().toString();
                intent.putExtra("Level", Integer.parseInt(selected));
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Ваш выбор: " + selected, Toast.LENGTH_SHORT);
                toast.show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //тестирование считывания данных из БД по кнопке
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        //Обработчик события по кнопке
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // лучше использовать ViewModel https://developer.android.com/topic/libraries/architecture/viewmodel
                new AsyncTask<Void, Void, List<Spell>>() {

                    // Обязательно НЕ в UI потоке, поэтому doInBackground
                    @Override
                    protected List<Spell> doInBackground(Void... voids) {
                        return AppDatabase.getInstance(MainActivity.this).spellDao().getAllSpells();
                    }


                    // Обязательно в UI потоке, поэтому onPostExecute
                    @Override
                    protected void onPostExecute(List<Spell> spells) {
                        textView.setText(spells.toString());
                    }
                }.execute();
            }
        });

    }

    //Кнопка переходна на второй активити
    @Override
    public void onClick(View v) {
        startActivity(intent);
    }
}
