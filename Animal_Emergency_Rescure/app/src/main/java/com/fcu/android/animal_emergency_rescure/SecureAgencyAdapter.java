package com.fcu.android.animal_emergency_rescure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SecureAgencyAdapter extends BaseAdapter {
    private Context context;
    private String[] agencyNames;
    private String[] distances;

    public SecureAgencyAdapter(Context context, String[] agencyNames, String[] distances) {
        this.context = context;
        this.agencyNames = agencyNames;
        this.distances = distances;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.agency_info, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.tv_agency_name);
        TextView distanceTextView = convertView.findViewById(R.id.tv_distance);

        nameTextView.setText(agencyNames[position]);
        distanceTextView.setText(distances[position]);

        return convertView;
    }
}

