package com.fcu.android.animal_emergency_rescure;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
    private Location currentLocation;

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

        btnExpandRescue.setOnClickListener(v -> toggleVisibility(rvSecureAgency, btnExpandRescue));
        btnExpandShelter.setOnClickListener(v -> toggleVisibility(rvShelter, btnExpandShelter));

        checkLocationPermissionAndSetup();
    }

    /**
     * 檢查位置權限並初始化
     */
    private void checkLocationPermissionAndSetup() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            getLastLocation();
            getDataFromFirebase();
            setupAdapters();
        }
    }

    /**
     * 請求權限結果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Location permission granted");
                getLastLocation();
                getDataFromFirebase();
                setupAdapters();
            } else {
                showLocationPermissionDeniedDialog();
            }
        }
    }

    /**
     * 顯示定位權限被拒絕的對話框
     */
    private void showLocationPermissionDeniedDialog() {
        new AlertDialog.Builder(this)
                .setTitle("定位權限被拒絕")
                .setMessage("應用需要定位權限以提供服務。請在設定中開啟定位權限。")
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 跳轉到設定頁面
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(android.net.Uri.parse("package:" + getPackageName()));
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消", null)
                .show();
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
                        currentLocation = location;
                        userLocation = location.getLatitude() + ", " + location.getLongitude();
                        logCurrentLocation();
                    } else {
                        userLocation = "24.178911851321303, 120.64655788648885"; // 逢甲大學
                    }
                });
    }

    /**
     * 紀錄當前位置
     */
    private void logCurrentLocation() {
        if (currentLocation != null) {
            Log.d(TAG, "Current Location: "+userLocation);
        } else {
            Log.d(TAG, "Current Location is null");
        }
    }

    /**
     * 初始化 Adapter
     */
    private void setupAdapters() {
        SecureAgencyAdapter secureAgencyAdapter = new SecureAgencyAdapter(this, secureAgencies, userLocation, apiKey);
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
                secureAgencies.clear(); // 清空列表，避免重複添加
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String url = snapshot.child("url").getValue(String.class);
                    String phoneNumber = snapshot.child("phoneNumber").getValue(String.class);
                    Double latitude = snapshot.child("location/latitude").getValue(Double.class);
                    Double longitude = snapshot.child("location/longitude").getValue(Double.class);
                    Log.d("FirebaseData", "Secure Agency - Name: " + name + ", URL: " + url +
                            ", Phone: " + phoneNumber + ", Location: " + latitude + "," + longitude);
                    String distance = CalculateDistance(latitude,longitude);
                    // 將安全機構加入列表
                    secureAgencies.add(new Agency(name, url, phoneNumber, new Agency.Location(latitude, longitude), distance));
                }
                setupAdapters();
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
                    String distance = CalculateDistance(latitude,longitude);
                    shelters.add(new Shelter(name, url, phoneNumber, new Shelter.Capacity(current, max), new Shelter.Location(latitude, longitude),distance));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("FirebaseData", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }
    private String  CalculateDistance(double latitude,double longitude){
        // 計算距離
        float[] results = new float[1];
        Location.distanceBetween(currentLocation.getLatitude(), currentLocation.getLongitude(),
                latitude, longitude, results);
        String distance = results[0] + " m"; // 將距離轉換為字符串，這裡需要根據實際需求進行格式化
        return distance;
    }
}
