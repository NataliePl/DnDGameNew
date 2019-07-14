package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Спиннеры
    String[] classes = {"Бард", "Волшебник", "Чародей", "Паладин", "Колдун", "Следопыт", "Друид", "Жрец"};
    String[] levels = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    //Работа с БД
    Button buttonSets;
    TextView textView;

    Intent intent;

    private SQLiteDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(MainActivity.this,SpellActivity.class);


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
//                Toast toast = Toast.makeText(getApplicationContext(),
//                        "Ваш выбор: " + selected, Toast.LENGTH_SHORT);
//                toast.show();
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
//                Toast toast = Toast.makeText(getApplicationContext(),
//                        "Ваш выбор: " + selected, Toast.LENGTH_SHORT);
//                toast.show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //тестирование считывания данных из БД по кнопке
        buttonSets = (Button) findViewById(R.id.but_save_set);


        //Обработчик события по кнопке
        buttonSets.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Intent SetsActivity = new Intent(MainActivity.this, SetsListActivity.class);
                                              startActivity(SetsActivity);

                                          }

                                          });

//                (new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                startActivity(MainActivity(this), );
//
//                // лучше использовать ViewModel https://developer.android.com/topic/libraries/architecture/viewmodel
//                new AsyncTask<Void, Void, List<Spell>>() {
//
//                    // Обязательно НЕ в UI потоке, поэтому doInBackground
//                    @Override
//                    protected List<Spell> doInBackground(Void... voids) {
//                        return AppDatabase.getInstance(MainActivity.this).spellDao().getAllSpells();
//                    }
//
//
//                    // Обязательно в UI потоке, поэтому onPostExecute
//                    @Override
//                    protected void onPostExecute(List<Spell> spells) {
//                        textView.setText(spells.toString());
//                    }
//                }.execute();
//            }
//        });

    }

    //Кнопка перехода на второй активити
    @Override
    public void onClick(View v) {
        startActivity(intent);
    }
}
