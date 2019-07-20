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
//пункт списка
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.spell_from_set, parent, false);
        }
        spellObjects.get(i);
        TextView level = view.findViewById(R.id.spellLevel);
        String levelSrt = spellObjects.get(i).getLevel();
        level.setText(levelSrt);

        TextView name = view.findViewById(R.id.spellName);
        name.setText(spellObjects.get(i).getName());

        return view;

    }

    Spell getSpell(int i) {
        return ((Spell) getItem(i));
    }
}
