package com.fcu.android.animal_emergency_rescure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SecureAgencyAdapter extends BaseAdapter {
    private Context context;
    private String[] secureAgencyName;
    private String[] distance;

    public SecureAgencyAdapter() {
    }
    public SecureAgencyAdapter(Context context, String[] secureAgencyName, String[] distance) {
        this.context = context;
        this.secureAgencyName = secureAgencyName;
        this.distance = distance;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            grid = new View(context);
            grid = inflater.inflate(R.layout.agency_info, null);
            TextView tvSecureAgencyName = grid.findViewById(R.id.tv_agency_name);
            TextView tvDistance = grid.findViewById(R.id.tv_distance);
        } else {
            grid = (View) convertView;
        }
        return grid;
    }
}
