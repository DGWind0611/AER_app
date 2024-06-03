package com.fcu.android.animal_emergency_rescure;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class SecureAgencyInfo extends AppCompatActivity {
    private GridView gvSecureAgency;
    private GridView gvShelter;

    private String[] secureAgencyName = {"台北市動物之家", "新北市動物之家", "桃園市動物保護教育園區", "新竹市動物收容所", "新竹縣動物收容所", "苗栗縣生態保育教育中心", "台中市動物之家", "彰化縣流浪動物收容中心", "南投縣公立動物收容所", "雲林縣流浪動物收容所", "嘉義市流浪動物收容所", "嘉義縣流浪動物收容所", "台南市動物之家", "高雄市動物保護教育園區", "屏東縣流浪動物收容所", "宜蘭縣流浪動物收容所", "花蓮縣流浪動物收容所", "台東縣流浪動物收容所", "澎湖縣流浪動物收容所", "金門縣動物收容中心", "連江縣動物收容中心"};
    private String[] distance = {"1.2km", "2.3km", "3.4km", "4.5km", "5.6km", "6.7km", "7.8km", "8.9km", "9.0km", "10.1km", "11.2km", "12.3km", "13.4km", "14.5km", "15.6km", "16.7km", "17.8km", "18.9km", "19.0km", "20.1km", "21.2km"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secure_agency_info);

        gvSecureAgency = findViewById(R.id.gv_secure_agency);
        gvShelter = findViewById(R.id.gv_shelter);

        SecureAgencyAdapter secureAgencyAdapter = new SecureAgencyAdapter(this, secureAgencyName, distance);
        gvSecureAgency.setAdapter(secureAgencyAdapter);

        gvSecureAgency.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SecureAgencyInfo.this, "你選取了 " + secureAgencyName[position], Toast.LENGTH_SHORT).show();

                // 创建Intent跳转到新的Activity
                Intent intent = new Intent(SecureAgencyInfo.this, SecureInfo2.class);
                intent.putExtra("agency_name", secureAgencyName[position]);
                intent.putExtra("distance", distance[position]);
                startActivity(intent);
            }
        });
    }
}
