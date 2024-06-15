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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShelterAdapter extends RecyclerView.Adapter<ShelterAdapter.ViewHolder> {
    private Context context;
    private List<Shelter> shelters;
    private String userLocation;
    private String shelterLocation;
    private String apiKey;


    public ShelterAdapter(Context context, List<Shelter> shelters, String userLocation, String apiKey) {
        this.context = context;
        this.shelters = shelters;
        this.userLocation = userLocation;
        this.apiKey = apiKey;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shelter_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Shelter shelter = shelters.get(position);
        Log.d("ShelterAdapter", "Shelter: " + shelter.name);
        Log.d("ShelterAdapter", "userLocation: " + userLocation);
        holder.tvShelterName.setText(shelter.name);
        shelterLocation = shelter.location.latitude + "," + shelter.location.longitude;
        DistanceCalculator.calculateDistance(context, userLocation, shelterLocation, apiKey, distance -> {
                    if (distance != null) {
                        holder.tvDistance.setText(distance);
                        Log.d("ShelterAdapter", "Distance calculated: " + distance);
                    } else {
                        holder.tvDistance.setText("無法獲取距離");
                        Log.e("ShelterAdapter", "Failed to calculate distance");
                    }
                });

        // 跳轉至地圖，並設定導航
        holder.mapButton.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + shelter.location.latitude + "," + shelter.location.longitude);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            context.startActivity(mapIntent);
        });

        holder.linkButton.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(shelter.url));
            context.startActivity(browserIntent);
        });

        holder.callButton.setOnClickListener(v -> {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse("tel:" + shelter.phoneNumber));
            context.startActivity(dialIntent);
        });
        holder.capacityProgressBar.setMax(shelter.capacity.max);
        holder.capacityProgressBar.setProgress(shelter.capacity.current);
    }

    @Override
    public int getItemCount() {
        return shelters.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvShelterName;
        TextView tvDistance;
        TextView tvCapacity;
        ProgressBar capacityProgressBar;
        ImageButton mapButton;
        ImageButton linkButton;
        ImageButton callButton;

        public ViewHolder(View itemView) {
            super(itemView);
            tvShelterName = itemView.findViewById(R.id.tv_shelter_name);
            tvDistance = itemView.findViewById(R.id.tv_shelter_distance);
            tvCapacity = itemView.findViewById(R.id.tv_capacity);
            capacityProgressBar = itemView.findViewById(R.id.pb_capacity);
            mapButton = itemView.findViewById(R.id.btn_location);
            linkButton = itemView.findViewById(R.id.btn_link);
            callButton = itemView.findViewById(R.id.btn_callout);
        }
    }
}
