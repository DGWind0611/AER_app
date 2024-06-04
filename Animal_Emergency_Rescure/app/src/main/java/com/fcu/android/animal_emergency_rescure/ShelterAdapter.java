package com.fcu.android.animal_emergency_rescure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ShelterAdapter extends BaseAdapter {
    private Context context;
    private String[] agencyNames;
    private String[] distances;
    private String[] capacities;

    public ShelterAdapter(Context context, String[] agencyNames, String[] distances, String[] capacities) {
        this.context = context;
        this.agencyNames = agencyNames;
        this.distances = distances;
        this.capacities = capacities;
    }

    @Override
    public int getCount() {
        return agencyNames.length;
    }

    @Override
    public Object getItem(int position) {
        return agencyNames[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.shelter_info, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.tv_agency_name);
        TextView distanceTextView = convertView.findViewById(R.id.tv_distance);
        TextView capacityTextView = convertView.findViewById(R.id.tv_capacity);
        ProgressBar capacityProgressBar = convertView.findViewById(R.id.gb_capacity);

        nameTextView.setText(agencyNames[position]);
        distanceTextView.setText(distances[position]);
        capacityTextView.setText(capacities[position]);

        String[] cap = capacities[position].split("/");
        int currentCapacity = Integer.parseInt(cap[0]);
        int maxCapacity = Integer.parseInt(cap[1]);

        capacityProgressBar.setMax(maxCapacity);
        capacityProgressBar.setProgress(currentCapacity);

        return convertView;
    }
}
