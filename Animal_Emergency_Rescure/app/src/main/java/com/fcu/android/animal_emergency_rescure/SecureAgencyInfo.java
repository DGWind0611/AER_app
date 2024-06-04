package com.fcu.android.animal_emergency_rescure;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

public class SecureAgencyInfo extends AppCompatActivity {
    private GridView gvSecureAgency;
    private GridView gvShelter;
    private ImageButton btnCloseAndExpand;
    private ImageButton btnCloseAndExpand2;

    private String[] secureAgencyName = {"台北市動物之家", "新北市動物之家", "桃園市動物保護教育園區", "新竹市動物收容所", "新竹縣動物收容所", "苗栗縣生態保育教育中心", "台中市動物之家", "彰化縣流浪動物收容中心", "南投縣公立動物收容所", "雲林縣流浪動物收容所", "嘉義市流浪動物收容所", "嘉義縣流浪動物收容所", "台南市動物之家", "高雄市動物保護教育園區", "屏東縣流浪動物收容所", "宜蘭縣流浪動物收容所", "花蓮縣流浪動物收容所", "台東縣流浪動物收容所", "澎湖縣流浪動物收容所", "金門縣動物收容中心", "連江縣動物收容中心"};
    private String[] distance = {"1.2km", "2.3km", "3.4km", "4.5km", "5.6km", "6.7km", "7.8km", "8.9km", "9.0km", "10.1km", "11.2km", "12.3km", "13.4km", "14.5km", "15.6km", "16.7km", "17.8km", "18.9km", "19.0km", "20.1km", "21.2km"};
    private String[] capacity = {"90/100", "50/100", "75/100", "60/100", "80/100", "55/100", "70/100", "65/100", "85/100", "40/100", "95/100", "50/100", "80/100", "70/100", "75/100", "60/100", "90/100", "55/100", "85/100", "70/100", "50/100"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secure_agency_info);

        gvSecureAgency = findViewById(R.id.gv_secure_agency);
        gvShelter = findViewById(R.id.gv_shelter);
        btnCloseAndExpand = findViewById(R.id.ibtn_close_and_expand);
        btnCloseAndExpand2 = findViewById(R.id.ibtn_close_and_expand_2);
        SecureAgencyAdapter secureAgencyAdapter = new SecureAgencyAdapter(this, secureAgencyName, distance);
        gvSecureAgency.setAdapter(secureAgencyAdapter);

        ShelterAdapter shelterAdapter = new ShelterAdapter(this, secureAgencyName, distance, capacity);
        gvShelter.setAdapter(shelterAdapter);

        View.OnClickListener toggleListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.ibtn_close_and_expand) {
                    toggleShelterVisibility(gvSecureAgency);
                } else if(v.getId() == R.id.ibtn_close_and_expand_2) {
                    toggleShelterVisibility(gvShelter);
                }
            }
        };
        btnCloseAndExpand.setOnClickListener(toggleListener);
        btnCloseAndExpand2.setOnClickListener(toggleListener);
    }

    private void toggleShelterVisibility(GridView gridView) {
        if (gridView.getVisibility() == View.VISIBLE) {
            btnCloseAndExpand.setImageResource(R.drawable.upload);
            gridView.setVisibility(View.GONE);
        } else {
            btnCloseAndExpand.setImageResource(R.drawable.down);
            gridView.setVisibility(View.VISIBLE);
        }
    }
}
