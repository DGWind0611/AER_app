package com.fcu.android.animal_emergency_rescure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class IllustratedBookCardAdapter extends BaseAdapter {
    private Context context;
    private List<Species> species;

    public IllustratedBookCardAdapter(Context context, List<Species> species) {
        this.context = context;
        this.species = species;
    }

    @Override
    public int getCount() {
        return species.size();
    }

    @Override
    public Object getItem(int i) {
        return species.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.illustrated_book_card,viewGroup,false);
        }

        ImageView speciesPic = view.findViewById(R.id.iv_species_pic);
        TextView speciesName = view.findViewById(R.id.tv_species_name);

        Species s = species.get(index);
        speciesPic.setImageResource(s.getSpeciesPicId());
        speciesName.setText(s.getSpeciesName());

        return view;
    }
}
