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
//        //Получение из предыдущего активити объекта Спелл
//        Spell spellObj = new Spell();
//       spellObj = (Spell) arguments.get("Spell");
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

        //Попытка вытащить из объекта класса Spell имя и присвоить его текстовому полю Активити. Выдает ошибку. Пыталась внести в поток работу - тоже с ошибками выходило.
//
//        TextView textViewSpellName = (TextView) findViewById(R.id.spellName);
//        textViewSpellName.setText("Проверка");


 //               textViewSpellName.setText(spellObj.name);


//        new AsyncTask<Void, Void, Spell>() {
//        @Override
//           protected Spell doInBackground(Void... voids) {
//


        //            List<SpellLevelName> spells = spellDao.getAllShortSpells();
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

                TextView textViewSpellMat = (TextView) findViewById(R.id.spellMat);
                textViewSpellMat.setText("Материал: "+curSpell.getSpell_material());

                TextView textViewSpellDur = (TextView) findViewById(R.id.spellDuringTime);
                textViewSpellDur.setText("Продолжительность: "+curSpell.getSpell_during_time());

                TextView textViewSpellReadTime = (TextView) findViewById(R.id.spellReadTime);
                textViewSpellReadTime.setText("Врема наложения: "+curSpell.getSpell_read_time());

                TextView textViewSpellDesc = (TextView) findViewById(R.id.spellDisc);
                textViewSpellDesc.setText("Описание: "+curSpell.getSpell_desc());


//                final List<Spell> spells2 = spells;
//                BaseAdapter adapter = new BaseAdapter() {
//                    @Override
//                    public int getCount() {
//                        return spells2.size();
//                    }
//                @Override
//                public Object getItem ( int i){
//                    return spells2.get(i);
//                }
//
//                @Override
//                public long getItemId ( int i){
//                    return spells2.get(i)._id;
//                }
//
//                public View getView(int position, View view, ViewGroup viewGroup) {
//                    if(view == null)
//                        view = LayoutInflater.from(OneSpellActivity.this).inflate(R.layout.spell, viewGroup, false);
//
//
//
//                    TextView level = view.findViewById(R.id.spellLevel);
//                    Integer levelSrt = spells2.get(position).level;
//                    level.setText(levelSrt.toString());
//                    TextView name = view.findViewById(R.id.spellName);
//                    name.setText(spells2.get(position).name);
//
//                    return view;
//                 }


//            };
//                ListView listView = findViewById(R.id.listShortSpell);
//                listView.setAdapter(adapter);
        }}.execute();
    }


    }






