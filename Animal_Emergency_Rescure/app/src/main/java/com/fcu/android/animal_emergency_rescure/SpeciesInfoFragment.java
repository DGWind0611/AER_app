package com.fcu.android.animal_emergency_rescure;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SpeciesInfoFragment extends Fragment {

    FragmentManager manager;
    private Spinner spCity;

    private ListView lvAnimals;
    private List<Animal> animalList;
    private AnimalAdapter animalAdapter;

    private ImageButton btnCloseFilter;
    private final String[] citys = {"台北市", "新北市", "桃園市", "台中市", "台南市", "高雄市", "基隆市", "新竹市", "嘉義市", "新竹縣", "苗栗縣", "彰化縣", "南投縣", "雲林縣", "嘉義縣", "屏東縣", "宜蘭縣", "花蓮縣", "台東縣", "澎湖縣", "金門縣", "連江縣"};

    private String cityStr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_species_info, container, false);

        btnCloseFilter = view.findViewById(R.id.btn_close_filter);
        lvAnimals = view.findViewById(R.id.lv_animals);
        animalList = new ArrayList<>();

        animalList.add(new Animal("臺灣山鷓鴣 Taiwan Partridge", "留鳥、普遍", "臺灣特有種", "三級保育類", "深山竹雞", "紅腳仔", "紅腳竹雞", R.drawable.account));
        animalList.add(new Animal("斯氏繡眼 Swinhoe's White-eye", "留鳥", "體綠，眼周白色", "", "綠繡眼", "暗綠繡眼鳥青啼仔", "", R.drawable.account));

        animalAdapter = new AnimalAdapter(getContext(), animalList);
        lvAnimals.setAdapter(animalAdapter);

        lvAnimals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Animal selectedAnimal = animalList.get(position);

                Intent intent = new Intent(getActivity(), SecureAgencyInfo.class);
                intent.putExtra("infoContent", selectedAnimal.getScientificName());
                startActivity(intent);
            }
        });
        btnCloseFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout filter = view.findViewById(R.id.filter_layout);
                filter.setVisibility(View.GONE);
            }
        });

        return view;

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