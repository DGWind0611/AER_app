package com.fcu.android.animal_emergency_rescure;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class SecureAgencyInfo extends AppCompatActivity {
    private GridView gvSecureAgency;
    private GridView gvShelter;
    private ImageButton btnCloseAndExpand;
    private ImageButton btnCloseAndExpand2;

    private String[] secureAgencyName = {"台北市動物之家", "新北市動物之家", "桃園市動物保護教育園區", "新竹市動物收容所", "新竹縣動物收容所", "苗栗縣生態保育教育中心", "台中市動物之家", "彰化縣流浪動物收容中心", "南投縣公立動物收容所", "雲林縣流浪動物收容所", "嘉義市流浪動物收容所", "嘉義縣流浪動物收容所", "台南市動物之家", "高雄市動物保護教育園區", "屏東縣流浪動物收容所", "宜蘭縣流浪動物收容所", "花蓮縣流浪動物收容所", "台東縣流浪動物收容所", "澎湖縣流浪動物收容所", "金門縣動物收容中心", "連江縣動物收容中心"};
    private String[] distance = {"1.2", "2.3", "3.4", "4.5", "5.6", "6.7", "7.8", "8.9", "9.0", "10.1", "11.2", "12.3", "13.4", "14.5", "15.6", "16.7", "17.8", "18.9", "19.0", "20.1", "21.2"};
    private String[] urls = {"http://agency1.com", "http://agency2.com", "http://agency3.com", "http://agency4.com", "http://agency5.com", "http://agency6.com", "http://agency7.com", "http://agency8.com", "http://agency9.com", "http://agency10.com", "http://agency11.com", "http://agency12.com", "http://agency13.com", "http://agency14.com", "http://agency15.com", "http://agency16.com", "http://agency17.com", "http://agency18.com", "http://agency19.com", "http://agency20.com", "http://agency21.com"};
    private String[] phoneNumbers = {"1234567890", "0987654321", "1122334455", "2233445566", "3344556677", "4455667788", "5566778899", "6677889900", "7788990011", "8899001122", "9900112233", "0011223344", "1122334455", "2233445566", "3344556677", "4455667788", "5566778899", "6677889900", "7788990011", "8899001122", "9900112233"};
    private String[] capacity = {"90/100", "50/100", "75/100", "60/100", "80/100", "55/100", "70/100", "65/100", "85/100", "40/100", "95/100", "50/100", "80/100", "70/100", "75/100", "60/100", "90/100", "55/100", "85/100", "70/100", "50/100"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secure_agency_info);

        gvSecureAgency = findViewById(R.id.gv_secure_agency);
        gvShelter = findViewById(R.id.gv_shelter);
        btnCloseAndExpand = findViewById(R.id.ibtn_close_and_expand);
        btnCloseAndExpand2 = findViewById(R.id.ibtn_close_and_expand_2);

        SecureAgencyAdapter secureAgencyAdapter = new SecureAgencyAdapter(this, secureAgencyName, distance, urls, phoneNumbers);
        gvSecureAgency.setAdapter(secureAgencyAdapter);

        ShelterAdapter shelterAdapter = new ShelterAdapter(this, secureAgencyName, distance, capacity,urls, phoneNumbers);
        gvShelter.setAdapter(shelterAdapter);

        btnCloseAndExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleShelterVisibility(gvShelter);
            }
        });

        btnCloseAndExpand2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleShelterVisibility(gvSecureAgency);
            }
        });
    }

    private void toggleShelterVisibility(GridView gridView) {
        if (gridView.getVisibility() == View.VISIBLE) {
            gridView.setVisibility(View.GONE);
        } else {
            gridView.setVisibility(View.VISIBLE);
        }
    }
}
