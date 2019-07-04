package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.ClassName;
import com.example.myapplication.db.Spell;
import com.example.myapplication.db.Class;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Спиннеры
    String[] classes = {"Волшебник", "Чародей", "Бард", "Паладин"};
    String[] levels = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    //Работа с БД
    Button button;
    TextView textView;

    private SQLiteDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO подключение к вьюМодел - метод не распознается - в документации он
 //       final MyViewModel viewModel = ViewModelProvider.of(this).get(MyViewModel.class);

        //спиннеры
        Spinner spinnerClasses = (Spinner) findViewById(R.id.spin_classes);

        ArrayAdapter<String> adapterClasses = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classes);
        adapterClasses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClasses.setAdapter(adapterClasses);

        new AsyncTask<Void, Void, List<Spell>>() {

            // Обязательно НЕ в UI потоке, поэтому doInBackground
            @Override
            protected className[] doInBackground(Void... voids) {
                return AppDatabase.getInstance(MainActivity.this).classDao().getAllNameClassesArr();
            }


            // Обязательно в UI потоке, поэтому onPostExecute
            @Override
            protected void onPostExecute(List<Spell> spells) {
                textView.setText(spells.toString());
            }
        }.execute();

        Spinner spinnerLevels = (Spinner) findViewById(R.id.spin_levels);
        ArrayAdapter<String> adapterLevels = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, levels);
        adapterLevels.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevels.setAdapter(adapterLevels);


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
        Intent intent = new Intent(MainActivity.this, SpellActivity.class);
        startActivity(intent);
    }
}
