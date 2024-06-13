package com.fcu.android.animal_emergency_rescure;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

public class ShelterAdapter extends RecyclerView.Adapter<ShelterAdapter.ViewHolder> {
    private Context context;
    private String[] shelterNames;
    private String[] urls;
    private String[] phoneNumbers;
    private String[] shelterLocations;
    private String userLocation;
    private String apiKey;

    public ShelterAdapter(Context context, String[] shelterNames, String[] urls,
                          String[] phoneNumbers, String[] shelterLocations,
                          String userLocation, String apiKey) {
        this.context = context;
        this.shelterNames = shelterNames;
        this.urls = urls;
        this.phoneNumbers = phoneNumbers;
        this.shelterLocations = shelterLocations;
        this.userLocation = userLocation;
        this.apiKey = apiKey;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shelter_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameTextView.setText(shelterNames[position]);

        DistanceCalculator.calculateDistance(context, userLocation, shelterLocations[position], apiKey, distance -> {
            if (distance != null) {
                holder.distanceTextView.setText(distance);
                Log.d("ShelterAdapter", "Distance calculated: " + distance);
            } else {
                holder.distanceTextView.setText("無法獲取距離");
                Log.e("ShelterAdapter", "Failed to calculate distance");
            }
        });

        holder.mapButton.setOnClickListener(v -> {
            String location = shelterLocations[position];
            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + location);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            context.startActivity(mapIntent);
        });

        holder.linkButton.setOnClickListener(v -> {
            String url = urls[position];
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(browserIntent);
        });

        holder.callButton.setOnClickListener(v -> {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse("tel:" + phoneNumbers[position]));
            context.startActivity(dialIntent);
        });

        // Assuming capacities are formatted as "current/max"
        String[] capacityParts = holder.capacityTextView.getText().toString().split("/");
        int currentCapacity = Integer.parseInt(capacityParts[0]);
        int maxCapacity = Integer.parseInt(capacityParts[1]);

        holder.capacityProgressBar.setMax(maxCapacity);
        holder.capacityProgressBar.setProgress(currentCapacity);
    }

    @Override
    public int getItemCount() {
        return shelterNames.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView distanceTextView;
        TextView capacityTextView;
        ProgressBar capacityProgressBar;
        ImageButton mapButton;
        ImageButton linkButton;
        ImageButton callButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_agency_name);
            distanceTextView = itemView.findViewById(R.id.tv_distance);
            capacityTextView = itemView.findViewById(R.id.tv_capacity);
            capacityProgressBar = itemView.findViewById(R.id.gb_capacity);
            mapButton = itemView.findViewById(R.id.ibtn_location);
            linkButton = itemView.findViewById(R.id.ibtn_link);
            callButton = itemView.findViewById(R.id.ibtn_callout);
        }
    }
}
