package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.App;
import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.Spell;
import com.example.myapplication.db.SpellDao;

import java.util.List;

import static java.lang.Long.parseLong;

public class OneSpellActivity extends AppCompatActivity {
String spell;
    //вывод описания одного заклинания



    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_spell);
        Bundle arguments = getIntent().getExtras();
        spell = arguments.get("Spell").toString();

        Log.i("Activ spell", spell);
////        final TextView textView = (TextView) findViewById(R.id.header);
//
//        //подход с разбором переменной spell в качестве строки, чтобы добраться до ID. Теперь предполагаю, что это лишнее
        String[] subStrSpell;
        String delimeter = ","; // Разделитель
        subStrSpell = spell.split(delimeter); // Разделения строки str с помощью метода split()
        String delimeter2 = "="; // Разделитель
        String[] subStrForId;
        subStrForId = subStrSpell[0].split(delimeter2);
        Log.i("RFRF", subStrSpell[0]);
        final Long idSpell = parseLong(subStrForId[1]);
        String a = idSpell.toString();
        Log.i("RFRF", a);

        Log.i("NameFromSpell",subStrSpell[0]);


        new AsyncTask<Void, Void, Spell>() {
        @Override
           protected Spell doInBackground(Void... voids) {
            AppDatabase db = App.getInstance().getDatabase();
            SpellDao spellDao = db.spellDao();
            Spell curSpell = new Spell();
            curSpell = AppDatabase.getInstance(OneSpellActivity.this).spellDao().getSpellById(idSpell);
            return curSpell;


        }
            @Override
            protected void onPostExecute(Spell curSpell) {

                TextView textViewSpellName = (TextView) findViewById(R.id.spellName);
                textViewSpellName.setText(curSpell.getName());

                TextView textViewSpellLevel = (TextView) findViewById(R.id.spellLevel);
                textViewSpellLevel.setText("Уровень: "+curSpell.getLevel());

                TextView textViewSpellMat = (TextView) findViewById(R.id.spellMaterial);
                textViewSpellMat.setText("Материал: "+curSpell.getSpell_material());

                TextView textViewSpellDur = (TextView) findViewById(R.id.spellDuringTime);
                textViewSpellDur.setText("Продолжительность: "+curSpell.getSpell_during_time());

                TextView textViewSpellReadTime = (TextView) findViewById(R.id.spellReadTime);
                textViewSpellReadTime.setText("Врема наложения: "+curSpell.getSpell_read_time());

                TextView textViewSpellDesc = (TextView) findViewById(R.id.spellDisc);
                textViewSpellDesc.setText("Описание: "+curSpell.getSpell_desc());


        }}.execute();
    }


    }






