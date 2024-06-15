package com.fcu.android.animal_emergency_rescure;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SecureAgencyAdapter extends RecyclerView.Adapter<SecureAgencyAdapter.ViewHolder> {
    private final Context context;
    private final List<Agency> agencies;
    private final String userLocation;
    private final String apiKey;


    public SecureAgencyAdapter(Context context, List<Agency> agencies, String userLocation, String apiKey) {
        this.context = context;
        this.agencies = agencies;
        this.userLocation = userLocation;
        this.apiKey = apiKey;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.agency_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Agency agency = agencies.get(position);
        holder.tvAgencyName.setText(agency.name);
        holder.tvDistance.setText(agency.distance);
        holder.tvAgencyName.setText(agency.name);
        //目的地座標
        String destination = agency.location.latitude + "," + agency.location.longitude;
        DistanceCalculator.calculateDistance(context, userLocation, destination, apiKey, distance -> {
            if (distance != null) {
                holder.tvDistance.setText(distance);
                Log.d("SecureAgencyAdapter", "Distance calculated: " + distance);
            } else {
                holder.tvDistance.setText("無法獲取距離");
                Log.e("SecureAgencyAdapter", "Failed to calculate distance");
            }
        });

        holder.ibtnLocation.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + agency.location.latitude + "," + agency.location.longitude);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            context.startActivity(mapIntent);
        });

        holder.ibtnLink.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(agency.url));
            context.startActivity(browserIntent);
        });

        holder.ibtnCallout.setOnClickListener(v -> {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse("tel:" + agency.phoneNumber));
            context.startActivity(dialIntent);
        });
    }

    @Override
    public int getItemCount() {
        return agencies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAgencyName, tvDistance;
        ImageButton ibtnLocation, ibtnLink, ibtnCallout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAgencyName = itemView.findViewById(R.id.tv_agency_name);
            tvDistance = itemView.findViewById(R.id.tv_distance);
            ibtnLocation = itemView.findViewById(R.id.ibtn_location);
            ibtnLink = itemView.findViewById(R.id.ibtn_link);
            ibtnCallout = itemView.findViewById(R.id.ibtn_callout);
        }
    }

}
