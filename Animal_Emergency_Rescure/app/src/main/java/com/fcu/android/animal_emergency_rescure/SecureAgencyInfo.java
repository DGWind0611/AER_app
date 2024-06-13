package com.fcu.android.animal_emergency_rescure;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SecureAgencyInfo extends AppCompatActivity {
    private static final String TAG = "SecureAgencyInfo";
    private RecyclerView rvSecureAgency;
    private RecyclerView rvShelter;
    private ImageButton btnExpandRescue;
    private ImageButton btnExpandShelter;
    private FusedLocationProviderClient fusedLocationClient;
    private String userLocation;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private String apiKey;
    private List<Agency> secureAgencies = new ArrayList<>();
    private List<Shelter> shelters = new ArrayList<>();

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
        // 檢查是否有取得定位權限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            getLastLocation();
        }

        btnExpandRescue.setOnClickListener(v -> toggleVisibility(rvSecureAgency, btnExpandRescue));
        btnExpandShelter.setOnClickListener(v -> toggleVisibility(rvShelter, btnExpandShelter));
        // 從 Firebase 取得資料
        getDataFromFirebase();
        setupAdapters();
    }

    /**
     * 取得使用者的位置
     */
    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        userLocation = location.getLatitude() + "," + location.getLongitude();
                    } else {
                        userLocation = "24.178911851321303, 120.64655788648885"; // 逢甲大學
                    }
                });
    }

    /**
     * 初始化 Adapter
     */
    private void setupAdapters() {
        SecureAgencyAdapter secureAgencyAdapter = new SecureAgencyAdapter(this,secureAgencies , userLocation, apiKey);
        rvSecureAgency.setLayoutManager(new LinearLayoutManager(this));
        rvSecureAgency.setAdapter(secureAgencyAdapter);

        ShelterAdapter shelterAdapter = new ShelterAdapter(this, shelters, userLocation, apiKey);
        rvShelter.setLayoutManager(new LinearLayoutManager(this));
        rvShelter.setAdapter(shelterAdapter);
    }

    /**
     * 切換顯示狀態
     *
     * @param view
     * @param button
     */
    private void toggleVisibility(View view, ImageButton button) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
            button.setImageResource(R.drawable.down); // 收起狀態圖片
        } else {
            view.setVisibility(View.VISIBLE);
            button.setImageResource(R.drawable.upload); // 展開狀態圖片
        }
    }

    /**
     * 從 Firebase 取得資料
     */
    private void getDataFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference secureAgenciesRef = database.getReference("secureAgencies");
        DatabaseReference sheltersRef = database.getReference("Shelters");

        secureAgenciesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String url = snapshot.child("url").getValue(String.class);
                    String phoneNumber = snapshot.child("phoneNumber").getValue(String.class);
                    Double latitude = snapshot.child("location/latitude").getValue(Double.class);
                    Double longitude = snapshot.child("location/longitude").getValue(Double.class);
                    Log.d("FirebaseData", "Secure Agency - Name: " + name + ", URL: " + url +
                            ", Phone: " + phoneNumber + ", Location: " + latitude + "," + longitude);
                    secureAgencies.add(new Agency(name, url, phoneNumber, new Agency.Location(latitude, longitude)));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("FirebaseData", "loadPost:onCancelled", databaseError.toException());
            }
        });

        sheltersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String url = snapshot.child("url").getValue(String.class);
                    String phoneNumber = snapshot.child("phoneNumber").getValue(String.class);
                    Integer current = Integer.valueOf(snapshot.child("capacity/current").getValue(String.class));
                    Integer max = Integer.valueOf(snapshot.child("capacity/max").getValue(String.class));
                    Double latitude = snapshot.child("location/latitude").getValue(Double.class);
                    Double longitude = snapshot.child("location/longitude").getValue(Double.class);
                    Log.d("FirebaseData", "Shelter - Name: " + name + ", URL: " + url +
                            ", Phone: " + phoneNumber + ", Capacity: " + current + "/" + max +
                            ", Location: " + latitude + "," + longitude);
                    shelters.add(new Shelter(name, url, phoneNumber, new Shelter.Capacity(current, max), new Shelter.Location(latitude, longitude)));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("FirebaseData", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

}
