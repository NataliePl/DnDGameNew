package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.db.App;
import com.example.myapplication.db.AppDatabase;
import com.example.myapplication.db.Spell;
import com.example.myapplication.db.SpellDao;

import static java.lang.Long.parseLong;

public class OneSpellActivity extends AppCompatActivity {
String spell;
Long idSpell;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_spell);
        Bundle arguments = getIntent().getExtras();
        spell = arguments.get("Spell").toString();
        idSpell = getIdFromSpell(spell);

        new AsyncTask<Void, Void, Spell>() {
        @Override
           protected Spell doInBackground(Void... voids) {

            Spell curSpell = getCurSlell(idSpell);
            return curSpell;

        }
            @Override
            protected void onPostExecute(Spell curSpell) {

            setInfo(curSpell);

        }}.execute();
    }

//Метод устанавливающий данные объекта Spell в textView activity
    private void setInfo(Spell curSpell) {
        TextView textViewSpellName = (TextView) findViewById(R.id.spellName);
        textViewSpellName.setText(curSpell.getName());

        TextView textViewSpellLevel = (TextView) findViewById(R.id.spellLevel);
        textViewSpellLevel.setText("Ур.: "+curSpell.getLevel());

        TextView textViewSpellMat = (TextView) findViewById(R.id.spellMaterial);
        textViewSpellMat.setText("Материал: "+curSpell.getSpell_material());

        TextView textViewSpellDur = (TextView) findViewById(R.id.spellDuringTime);
        textViewSpellDur.setText("Продолжительность: "+curSpell.getSpell_during_time());

        TextView textViewSpellReadTime = (TextView) findViewById(R.id.spellReadMatDisTime);
        String comp = "Врема наложения: "+curSpell.getSpell_read_time()+ "\n\nДистанция: "+ curSpell.getSpell_distance()+ "\n\nПрирода: "+ curSpell.getSpell_nature();
        textViewSpellReadTime.setText(comp);

        TextView textViewSpellDesc = (TextView) findViewById(R.id.spellDisc);
        textViewSpellDesc.setText(curSpell.getSpell_desc());
        textViewSpellDesc.setMovementMethod(new ScrollingMovementMethod());
    }

//извлечение выбранного объекта Spell
    private Spell getCurSlell(Long idSpell) {

        AppDatabase db = App.getInstance().getDatabase();
        SpellDao spellDao = db.spellDao();
        Spell curSpell = new Spell();
        curSpell = AppDatabase.getInstance(OneSpellActivity.this).spellDao().getSpellById(idSpell);
        return curSpell;
    }

    private long getIdFromSpell(String spell) {
        String[] subStrSpell;
        String delimeter = ",";
        subStrSpell = spell.split(delimeter);
        String delimeter2 = "=";
        String[] subStrForId;
        subStrForId = subStrSpell[0].split(delimeter2);
        final Long idSpell = parseLong(subStrForId[1]);
        return idSpell;
    }

}






