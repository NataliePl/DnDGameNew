package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SetSpellListActivity extends AppCompatActivity {
String setUser;
TextView textTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_spell_list);
        Bundle arguments = getIntent().getExtras();
        setUser = arguments.get("Set").toString();

        Log.i("Send from set", setUser);
        textTest = findViewById(R.id.textView3);
        textTest.setText(setUser);

    }
}
