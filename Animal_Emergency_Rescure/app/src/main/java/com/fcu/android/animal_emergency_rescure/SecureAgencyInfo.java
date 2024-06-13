package com.fcu.android.animal_emergency_rescure;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class SecureAgencyInfo extends AppCompatActivity {
    private RecyclerView rvSecureAgency;
    private RecyclerView rvShelter;
    private ImageButton btnExpandRescue;
    private ImageButton btnExpandShelter;
    private FusedLocationProviderClient fusedLocationClient;
    private String userLocation;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private String apiKey;
    private String[] secureAgencyName = {"台中市動物保護防疫處-南屯園區", "台中市動物保護防疫處-后里園區"};
    private String[] urls = {"http://agency1.com", "http://agency2.com"};
    private String[] phoneNumbers = {"1234567890", "0987654321"};
    private String[] capacity = {"90/100", "50/100"};
    private String[] agencyLocations = {"24.143811469054892, 120.58105027116473", "24.286489983079235, 120.70955517116475"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secure_agency_info);

        rvSecureAgency = findViewById(R.id.rv_secure_agency);
        rvShelter = findViewById(R.id.rv_shelter);
        btnExpandRescue = findViewById(R.id.ibtn_expand_rescue);
        btnExpandShelter = findViewById(R.id.ibtn_expand_shelter);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        apiKey = getString(R.string.google_maps_key);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            getLastLocation();
        }

        btnExpandRescue.setOnClickListener(v -> toggleVisibility(rvSecureAgency, btnExpandRescue));
        btnExpandShelter.setOnClickListener(v -> toggleVisibility(rvShelter, btnExpandShelter));
    }




    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        userLocation = location.getLatitude() + "," + location.getLongitude();
                    } else {
                        userLocation = "24.178911851321303, 120.64655788648885"; // 逢甲大學
                    }
                    setupAdapters();
                });
    }

    private void setupAdapters() {
        SecureAgencyAdapter secureAgencyAdapter = new SecureAgencyAdapter(this, secureAgencyName, urls, phoneNumbers, agencyLocations, userLocation, apiKey);
        rvSecureAgency.setLayoutManager(new LinearLayoutManager(this));
        rvSecureAgency.setAdapter(secureAgencyAdapter);

        ShelterAdapter shelterAdapter = new ShelterAdapter(this, secureAgencyName, urls, phoneNumbers, agencyLocations, userLocation, apiKey);
        rvShelter.setLayoutManager(new LinearLayoutManager(this));
        rvShelter.setAdapter(shelterAdapter);
    }

    private void toggleVisibility(View view, ImageButton button) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
            button.setImageResource(R.drawable.down); // 收起狀態圖片
        } else {
            view.setVisibility(View.VISIBLE);
            button.setImageResource(R.drawable.upload); // 展開狀態圖片
        }
    }
}
