package com.fcu.android.animal_emergency_rescure;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ShelterAdapter extends BaseAdapter {
    private Context context;
    private String[] agencyNames;
    private String[] distances;
    private String[] capacities;

    private String[] urls;
    private String[] phoneNumbers;


    public ShelterAdapter(Context context, String[] agencyNames, String[] distances, String[] capacities, String[] urls, String[] phoneNumbers) {
        this.context = context;
        this.agencyNames = agencyNames;
        this.distances = distances;
        this.capacities = capacities;
        this.urls = urls;
        this.phoneNumbers = phoneNumbers;
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
        ImageButton linkButton = convertView.findViewById(R.id.ibtn_link);
        ImageButton callButton = convertView.findViewById(R.id.ibtn_callout);
        ImageButton mapButton = convertView.findViewById(R.id.ibtn_location);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + agencyNames[position]);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);
            }
        });
        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urls[position];
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(browserIntent);
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(phoneNumbers[position]);
            }
        });


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

    private void dialPhoneNumber(String phoneNumber) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse("tel:" + phoneNumber));
        context.startActivity(dialIntent);
    }

    private String formatDistance(String distance) {
        try {
            double distanceInKm = Double.parseDouble(distance);
            return distanceInKm + " km";
        } catch (NumberFormatException e) {
            return distance;
        }
    }
}
