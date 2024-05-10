package com.fcu.android.animal_emergency_rescure;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class SecureFragment extends Fragment {

    private Spinner spCity;
    private final String[] citys={"台北市","新北市","桃園市","台中市","台南市","高雄市","基隆市","新竹市","嘉義市","新竹縣","苗栗縣","彰化縣","南投縣","雲林縣","嘉義縣","屏東縣","宜蘭縣","花蓮縣","台東縣","澎湖縣","金門縣","連江縣"};

    private String cityStr;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_secure, container, false);

        //        // Get the outer layout
//        RelativeLayout outerLayout = findViewById(R.layout.activity_main);
//
//        // Inflate the inner layout
//        View innerLayout = getLayoutInflater().inflate(R.layout.filter, null);
//
//        // Add the inner layout to the outer layout
//        outerLayout.addView(innerLayout);


//        //city相關篩選spinner
//        spCity = spCity.findViewById(R.id.sp_city);
//        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this, R.layout.dropdown_city, citys);
//
//        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spCity.setAdapter(adapterCity);
//        AdapterView.OnItemSelectedListener spCityListener = new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                cityStr = citys[position];
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                cityStr=" ";
//            }
//        };
//        spCity.setOnItemSelectedListener(spCityListener);
    }
}