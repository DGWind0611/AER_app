package com.fcu.android.animal_emergency_rescure;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecureInfo2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secure_info2);

        TextView tvAgencyName = findViewById(R.id.tv_agency_name);
        TextView tvDistance = findViewById(R.id.tv_distance);

        // 获取传递过来的数据
        String agencyName = getIntent().getStringExtra("agency_name");
        String distance = getIntent().getStringExtra("distance");

        // 设置TextView内容
        tvAgencyName.setText(agencyName);
        tvDistance.setText(distance);
    }
}
