package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String[] classes = {"Бард", "Волшебник", "Чародей", "Паладин", "Колдун", "Следопыт", "Друид", "Жрец"};
    String[] levels = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    Button buttonSets;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(MainActivity.this, SpellActivity.class);
        setSpinnerClasses(classes);
        setSpinnerLevels(levels);
        //Обработчик события по кнопке перехода на активити со списком заклинаний
        buttonSets = (Button) findViewById(R.id.but_save_set);
        buttonSets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SetsActivity = new Intent(MainActivity.this, SetsListActivity.class);
                startActivity(SetsActivity);
            }

        });

    }

//методы заполнения спиннеров
    private void setSpinnerClasses(String[] classes) {

        final Spinner spinnerClasses = (Spinner) findViewById(R.id.spin_classes);
        ArrayAdapter<String> adapterClasses = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classes);
        adapterClasses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClasses.setAdapter(adapterClasses);
        spinnerClasses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String selected = spinnerClasses.getSelectedItem().toString();
                intent.putExtra("Class", selected);

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    private void setSpinnerLevels(String[] levels) {

        final Spinner spinnerLevels = (Spinner) findViewById(R.id.spin_levels);
        ArrayAdapter<String> adapterLevels = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, levels);
        adapterLevels.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevels.setAdapter(adapterLevels);
        spinnerLevels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String selected = spinnerLevels.getSelectedItem().toString();
                intent.putExtra("Level", Integer.parseInt(selected));

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    //Кнопка перехода на второй активити
    @Override
    public void onClick(View v) {
        startActivity(intent);
    }
}
