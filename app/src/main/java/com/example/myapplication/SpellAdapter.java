package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.myapplication.db.Spell;
import java.util.List;

public class SpellAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    List<Spell> spellObjects;


    SpellAdapter(Context context, List<Spell> spells) {
        ctx = context;
        spellObjects = spells;
        lInflater=(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return spellObjects.size();
    }

    @Override
    public Object getItem(int i) {
        return spellObjects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.spell_from_set, parent, false);
        }

        Spell s = getSpell(i);

        //Заполнение пунктов
        TextView level = view.findViewById(R.id.spellLevel);
//        CheckBox checkBox = view.findViewById(R.id.checkBox);

//        checkBox.setChecked(spellObjects.contains(s));
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
//                if (checked)
//                    spellObjects.add(s);
//                else
//                    spellObjects.remove(s);
//            }
//        });
                int levelSrt = s.level; // todo getter?
                level.setText(String.valueOf(levelSrt)); // Don't use Integer. You can use String.valueOf(levelSrt)
                TextView name = view.findViewById(R.id.spellName);
                name.setText(s.name);
        return view;
    }

    Spell getSpell(int i) {
        return ((Spell) getItem(i));
    }
}
