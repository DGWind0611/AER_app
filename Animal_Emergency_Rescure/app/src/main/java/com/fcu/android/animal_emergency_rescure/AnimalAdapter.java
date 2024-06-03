package com.fcu.android.animal_emergency_rescure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AnimalAdapter extends BaseAdapter {
    private Context context;
    private List<Animal> animals;
    private LayoutInflater inflater;

    public AnimalAdapter(Context context, List<Animal> animals) {
        this.context = context;
        this.animals = animals;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return animals.size();
    }

    @Override
    public Object getItem(int position) {
        return animals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.animal_information_card, parent, false);
        }
        Animal animal = animals.get(position);

        TextView textView = convertView.findViewById(R.id.textView);
        TextView textView3 = convertView.findViewById(R.id.textView3);
        TextView textView4 = convertView.findViewById(R.id.textView4);
        TextView textView6 = convertView.findViewById(R.id.textView6);
        TextView tv_common_name_1 = convertView.findViewById(R.id.tv_common_name_1);
        TextView tv_common_name_2 = convertView.findViewById(R.id.tv_common_name_2);
        TextView tv_common_name_3 = convertView.findViewById(R.id.tv_common_name_3);
        ImageView imageView3 = convertView.findViewById(R.id.imageView3);

        textView.setText(animal.getScientificName());
        textView3.setText(animal.getStatus());
        textView4.setText(animal.getEndemic());
        textView6.setText(animal.getConservation());
        tv_common_name_1.setText(animal.getCommonName1());
        tv_common_name_2.setText(animal.getCommonName2());
        tv_common_name_3.setText(animal.getCommonName3());
        imageView3.setImageResource(animal.getImageResId());


        return convertView;
    }
}

